package com.liukang.tirrger.controller;

import com.liukang.tirrger.enumeration.SexEnum;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.UserService;
import com.liukang.tirrger.view.PdfExportService;
import com.liukang.tirrger.view.PdfView;
import com.liukang.tirrger.vo.UserVo;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestTestController {
    @Autowired
    private UserService userService;
    @GetMapping("/restful")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("restful/restful");

        return mv;
    }
    private User changeToPo(UserVo userVo){
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setNote(userVo.getNote());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        return user;
    }
    private UserVo changeToVo(User user){
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setNote(user.getNote());
        userVo.setUserName(user.getUserName());
        //userVo.setSexCode(user.getSex().getId());
       // userVo.setSexName(user.getSex().getName());
        return userVo;
    }
    private List<UserVo> changeTOVoes(List<User> poList){
        List<UserVo> voList = new ArrayList<>();
        for(User user : poList){
            UserVo userVo = changeToVo(user);
            voList.add(userVo);
        }
        return voList;

    }
    @PostMapping("/user")
    @ResponseBody
    public User insertUser(@RequestBody UserVo userVo){
        User user = changeToPo(userVo);
        return userService.insertUser(user);
    }
    @GetMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return changeToVo(user);
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
