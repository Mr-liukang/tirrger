package com.liukang.tirrger.controller;



import com.liukang.tirrger.pojo.User;

import com.liukang.tirrger.service.UserService;
import com.liukang.tirrger.view.PdfExportService;
import com.liukang.tirrger.view.PdfView;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
       return userService.getUser(id);
    }
    @GetMapping("/show")
    public String show(Long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user/details";
    }
    @GetMapping("/showUser")
    public String show(User user, Model model){

        return "user/details";
    }
    @GetMapping("/redirect1")
    public String redirect1(Long id, RedirectAttributes ra) {
        User user = new User();
        user.setNote("汪国真");
        user.setId(id);
        user.setUserName("username");
        ra.addFlashAttribute("user",user);
        return "redirect:/user/showUser";
    }
    @GetMapping("/redirect2")
    public ModelAndView redirect2(Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/user/show?id="+id);
        return mv;
    }
    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser(Long id,String userName,String note){
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        user.setId(id);
        return userService.insertUser(user);
    }
    @RequestMapping("details")
    public ModelAndView details(Long id){
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user",user);
        return mv;
    }
    @RequestMapping("table")
    public ModelAndView table(){
        List<User> users = userService.findUsers(null,null);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/table");
        mv.addObject("userList",users);
        return mv;
    }
    @RequestMapping("list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName",required = false) String userName,
                           @RequestParam(value = "note",required = false) String note){
        List<User> users = userService.findUsers(userName,note);

        return users;
    }
    @RequestMapping("updateUserName")
    @ResponseBody
    public Map<String,Object> updateUserName(Long id, String userName){
        User user = userService.updateUser(id,userName);
        boolean flag = user !=null;
        String message = flag?"更新成功":"更新失败";
        return resultMap(flag,message);
    }
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUser(Long id){
        int result = userService.deleteUser(id);
        boolean flag = result ==1;
        String message = flag?"删除成功":"删除失败";
        return resultMap(flag,message);
    }
    private Map<String,Object> resultMap(boolean flag,String message){
        Map<String,Object> result = new HashMap<>();
        result.put("success",flag);
        result.put("message",message);
        return result;
    }
    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(){
        List<User> users = userService.findUsers(null, null);
        View View = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        mv.setView(View);
        mv.addObject("userList",users);
        return mv;

    }
    @SuppressWarnings("unchecked")
    private PdfExportService exportService(){

      return ((model, document, pdfWriter, request) -> {
          try{
              document.setPageSize(PageSize.A4);
              document.addTitle("用户信息");
              document.add(new Chunk("\n"));
              PdfPTable table = new PdfPTable(3);
              PdfPCell cell = null;
              Font f8 = new Font(BaseFont.createFont("/simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));
              f8.setColor(Color.blue);
              f8.setStyle(Font.BOLD);
              Font f9 = new Font(BaseFont.createFont("/simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));

             // f8.setFamily("SimSun");
              cell = new PdfPCell(new Paragraph("id", f8));
              cell.setHorizontalAlignment(1);
              table.addCell(cell);
              cell = new PdfPCell(new Paragraph("姓名",f8));
              cell.setHorizontalAlignment(1);
              table.addCell(cell);
              cell = new PdfPCell(new Paragraph("备注",f8));
              cell.setHorizontalAlignment(1);
              table.addCell(cell);
              List<User> userList = (List<User>)model.get("userList");
              for(User user : userList){
                  document.add(new Chunk("\n"));
                  cell = new PdfPCell(new Paragraph(user.getId()+"",f9));
                  table.addCell(cell);
                  cell = new PdfPCell(new Paragraph(user.getUserName()+"",f9));
                  table.addCell(cell);
                  cell = new PdfPCell(new Paragraph(user.getNote()==null?"":user.getNote()+"",f9));
                  table.addCell(cell);

              }
              document.add(table);
          }catch (Exception e){

          }
      });
    }


}
