package com.maxxis.utils;

import com.maxxis.domain.Test;
import com.maxxis.domain.TestClietTyre;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PoiUtilsDai {

    // 将数据导出成excel文件
    public static ResponseEntity<byte[]> exportUser2Excel(List<TestClietTyre> test) {

        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;

        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("待排计划");
            //3.2设置文档管理员
            dsi.setManager("admin");
            //3.3设置组织机构
            dsi.setCompany("maxxis");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("待排计划表");
            //4.2.设置文档标题
            si.setTitle("待排计划");
            //4.3 设置文档作者
            si.setAuthor("admin");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("待排计划表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 12 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 12 * 256);
            sheet.setColumnWidth(4, 12 * 256);
            sheet.setColumnWidth(5, 12 * 256);
            sheet.setColumnWidth(6, 12 * 256);
            sheet.setColumnWidth(7, 12 * 256);
            sheet.setColumnWidth(8, 12 * 256);
            sheet.setColumnWidth(9, 10 * 256);
            sheet.setColumnWidth(10, 12 * 256);
            sheet.setColumnWidth(11, 12 * 256);
            sheet.setColumnWidth(12, 12 * 256);
            sheet.setColumnWidth(13, 12 * 256);
            sheet.setColumnWidth(14, 12 * 256);
            sheet.setColumnWidth(15, 12 * 256);
            sheet.setColumnWidth(16, 10 * 256);
            sheet.setColumnWidth(17, 12 * 256);
            sheet.setColumnWidth(18, 12 * 256);
            sheet.setColumnWidth(19, 12 * 256);
            sheet.setColumnWidth(20, 12 * 256);
            sheet.setColumnWidth(21, 12 * 256);
            sheet.setColumnWidth(22, 12 * 256);
            sheet.setColumnWidth(23, 10 * 256);
            sheet.setColumnWidth(24, 12 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("顺位");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("需求编号");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("需求日期");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("项目名称");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("项目工程师");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("电话");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("紧急程度");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("轮胎编号");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("试验项目");
            cell8.setCellStyle(headerStyle);
            HSSFCell cell9 = headerRow.createCell(9);
            cell9.setCellValue("法规");
            cell9.setCellStyle(headerStyle);
            HSSFCell cell10 = headerRow.createCell(10);
            cell10.setCellValue("轮胎规格");
            cell10.setCellStyle(headerStyle);
            HSSFCell cell11 = headerRow.createCell(11);
            cell11.setCellValue("试验气压");
            cell11.setCellStyle(headerStyle);
            HSSFCell cell12 = headerRow.createCell(12);
            cell12.setCellValue("试验载荷");
            cell12.setCellStyle(headerStyle);
            HSSFCell cell13 = headerRow.createCell(13);
            cell13.setCellValue("试验轮辋");
            cell13.setCellStyle(headerStyle);
            HSSFCell cell14 = headerRow.createCell(14);
            cell14.setCellValue("试验速度");
            cell14.setCellStyle(headerStyle);
            HSSFCell cell15 = headerRow.createCell(15);
            cell15.setCellValue("倾斜角");
            cell15.setCellStyle(headerStyle);
            HSSFCell cell16 = headerRow.createCell(16);
            cell16.setCellValue("滑移角");
            cell16.setCellStyle(headerStyle);
            HSSFCell cell17 = headerRow.createCell(17);
            cell17.setCellValue("前后仰角");
            cell17.setCellStyle(headerStyle);
            HSSFCell cell18 = headerRow.createCell(18);
            cell18.setCellValue("里程");
            cell18.setCellStyle(headerStyle);
            HSSFCell cell19 = headerRow.createCell(19);
            cell19.setCellValue("order");
            cell19.setCellStyle(headerStyle);
            HSSFCell cell20 = headerRow.createCell(20);
            cell20.setCellValue("pt");
            cell20.setCellStyle(headerStyle);
            HSSFCell cell21 = headerRow.createCell(21);
            cell21.setCellValue("FZ");
            cell21.setCellStyle(headerStyle);
            HSSFCell cell22 = headerRow.createCell(22);
            cell22.setCellValue("委试目的");
            cell22.setCellStyle(headerStyle);
            HSSFCell cell23 = headerRow.createCell(23);
            cell23.setCellValue("样品处理");
            cell23.setCellStyle(headerStyle);
            HSSFCell cell24 = headerRow.createCell(24);
            cell24.setCellValue("目标");
            cell24.setCellStyle(headerStyle);
            //6.装数据
            for (int i = 0; i < test.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                TestClietTyre test1= test.get(i);
                row.createCell(0).setCellValue(test1.getNumber());
                row.createCell(1).setCellValue(test1.getRequestId());
                row.createCell(2).setCellValue(test1.getExpectedDateStr());
                row.createCell(3).setCellValue(test1.getProject());
                row.createCell(4).setCellValue(test1.getProjectEngineer());
                row.createCell(5).setCellValue(test1.getContactExt());
                row.createCell(6).setCellValue(test1.getPriorityDes());
                row.createCell(7).setCellValue(test1.getSampleID());
                row.createCell(8).setCellValue(test1.getTestItem());
                row.createCell(9).setCellValue(test1.getStandard());
                row.createCell(10).setCellValue(test1.getSize());
                row.createCell(11).setCellValue(test1.getPt());
                row.createCell(12).setCellValue(test1.getFz());
                row.createCell(13).setCellValue(test1.getUsageRim());
                row.createCell(14).setCellValue(test1.getVr());
                row.createCell(15).setCellValue(test1.getCA());
                row.createCell(16).setCellValue(test1.getSA());
                row.createCell(17).setCellValue(test1.getPA());
                row.createCell(18).setCellValue(test1.getMileage());
                row.createCell(19).setCellValue(test1.getFFTOrder());
                row.createCell(20).setCellValue(test1.getFlatSpotPt());
                row.createCell(21).setCellValue(test1.getFlatSpotFz());
                row.createCell(22).setCellValue(test1.getPurpose());
                row.createCell(23).setCellValue(test1.getSampleDisposal());
                row.createCell(24).setCellValue(test1.getTarget());

            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("待排计划表.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);

    }

}

