package com.ldj.test;

public class test {
    public static void main(String[] args) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        // 定义导出的excel名字
        String excelName = "专题分析报表";
        try {
            @SuppressWarnings("resource")
            HSSFWorkbook wb = new HSSFWorkbook();
            // 工作簿
            HSSFSheet sheet = wb.createSheet("专题分析");
            HSSFRow row = null;
            int columnIndex = 0;
            int rowIndex = 0;

            ThemeAnalyseReportResp themeAnalyseReport = themeAnalysisService.getThemeAnalyseReportRecord(req);
            // 总数
            Long total = themeAnalyseReport.getTotal();
            // 管道数据
            AnalyseReportData pipeLineData = themeAnalyseReport.getPipeLineData();
            // 雨河数据
            AnalyseReportData rainRiverData = themeAnalyseReport.getRainRiverData();
            // 雨污数据
            AnalyseReportData rainSewageData = themeAnalyseReport.getRainSewageData();
            Long pipeTotal = pipeLineData.getPipeTotal();
            Long rainSewageTotal = rainSewageData.getPipeTotal();
            Long rainRiverTotal = rainRiverData.getPipeTotal();
            // 时间范围
            Date startTime = req.getStartTime();
            Date endTime = req.getEndTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String startTimeFormat = sdf.format(startTime);
            String endTimeFormat = sdf.format(endTime);
            String timeStr = startTimeFormat + "-" + endTimeFormat;
            // 创建所有行数
            // 标题、名称所占行数 至少一行
            if (total != 0) {
                Long n = 0l;
                if (pipeTotal == 0) {
                    n++;
                }
                if (rainSewageTotal == 0) {
                    n++;
                }
                if (rainRiverTotal == 0) {
                    n++;
                }
                // 导出行数为最大值
                if (total > 65535) {
                    total = 65530l;
                }

                for (int m = 0; m < total + n; m++) {
                    row = sheet.createRow(m);
                }
            } else {
                for (int m = 0; m < 5; m++) {
                    row = sheet.createRow(m);
                }
            }
            // 合并单元格后居中
            HSSFCellStyle cellStyle = wb.createCellStyle();
            // 垂直居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 设置边框
            // 表格标题
            // HSSFRow row0 = sheet.createRow(rowIndex);
            row = sheet.getRow(rowIndex);
            HSSFCell cell = row.createCell(0);
            // 加载单元格样式
            // cell.setCellStyle(headStyle);
            cell.setCellValue("智水系统/专题分析报表");
            cell.setCellStyle(cellStyle);
            // 定义表头
            rowIndex++;
            row = sheet.createRow(rowIndex);
            row.setHeight((short) (22.50 * 20));// 设置行高
            columnIndex = 0;
            String str0 = "专题分析名称";
            row.createCell(columnIndex).setCellValue("专题分析名称");
            row.createCell(++columnIndex).setCellValue("报表周期");
            row.createCell(++columnIndex).setCellValue("总数");
            row.createCell(++columnIndex).setCellValue("数据名称");
            row.createCell(++columnIndex).setCellValue("总发生次数");
            row.createCell(++columnIndex).setCellValue("站点名称");
            row.createCell(++columnIndex).setCellValue("发生次数");
            row.createCell(++columnIndex).setCellValue("相关性站点");
            row.createCell(++columnIndex).setCellValue("日期");
            // 设置列宽
            int length = str0.getBytes().length;
            sheet.setColumnWidth(0, (short) (length * 256));
            sheet.setColumnWidth(1, (short) (length * 256));
            sheet.setColumnWidth(2, (short) (length * 128));
            sheet.setColumnWidth(3, (short) (length * 256));
            sheet.setColumnWidth(4, (short) (length * 128));
            sheet.setColumnWidth(5, (short) (length * 256));
            sheet.setColumnWidth(6, (short) (length * 128));
            sheet.setColumnWidth(7, (short) (length * 256));
            sheet.setColumnWidth(8, (short) (length * 256));

            // 单元格合并 起始行,结束行,起始列,结束列
            // 标题
            CellRangeAddress callRangeAddress0 = new CellRangeAddress(0, 0, 0, 8);
            sheet.addMergedRegion(callRangeAddress0);

            // 重新设置边框样式
            // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress0,
            // sheet, wb);
            // 第三行数据
            rowIndex++;
            // row = sheet.createRow(rowIndex);
            row = sheet.getRow(rowIndex);
            if (total.intValue() > 0) {
                // 专题分析名称
                CellRangeAddress callRangeAddress = new CellRangeAddress(rowIndex, total.intValue() + rowIndex - 1, 0,
                        0);
                sheet.addMergedRegion(callRangeAddress);
                // 重新设置边框样式
                RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress, sheet, wb);
                // 报表周期
                CellRangeAddress callRangeAddress1 = new CellRangeAddress(rowIndex, total.intValue() + rowIndex - 1, 1,
                        1);
                sheet.addMergedRegion(callRangeAddress1);
                // 重新设置边框样式
                RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress1, sheet, wb);
                // 总数
                CellRangeAddress callRangeAddress2 = new CellRangeAddress(rowIndex, total.intValue() + rowIndex - 1, 2,
                        2);
                sheet.addMergedRegion(callRangeAddress2);
                // 重新设置边框样式
                // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress2,
                // sheet, wb);
            } else {
                // 专题分析名称
                CellRangeAddress callRangeAddress = new CellRangeAddress(2, 4, 0, 0);
                sheet.addMergedRegion(callRangeAddress);
                // 重新设置边框样式
                RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress, sheet, wb);
                // 报表周期
                CellRangeAddress callRangeAddress1 = new CellRangeAddress(2, 4, 1, 1);
                sheet.addMergedRegion(callRangeAddress1);
                // 重新设置边框样式
                RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress1, sheet, wb);
                // 总数
                CellRangeAddress callRangeAddress2 = new CellRangeAddress(2, 4, 2, 2);
                sheet.addMergedRegion(callRangeAddress2);
            }
            columnIndex = 0;
            cell = row.createCell(columnIndex);
            cell.setCellValue("专题分析报表概览");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(++columnIndex);
            cell.setCellValue(timeStr);
            cell.setCellStyle(cellStyle);
            cell = row.createCell(++columnIndex);
            cell.setCellValue(total);
            cell.setCellStyle(cellStyle);
            // 表格数据填充
            // 雨污
            if (rainSewageData != null) {
                if (rainSewageTotal.intValue() > 0) { // 合并单元格行
                    // 雨污相关性
                    CellRangeAddress callRangeAddress3 = new CellRangeAddress(rowIndex,
                            rainSewageTotal.intValue() + rowIndex - 1, 3, 3);
                    sheet.addMergedRegion(callRangeAddress3);
                    // 重新设置边框样式
                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress3,
                    // sheet, wb);
                    // 数目
                    CellRangeAddress callRangeAddress4 = new CellRangeAddress(rowIndex,
                            rainSewageTotal.intValue() + rowIndex - 1, 4, 4);
                    sheet.addMergedRegion(callRangeAddress4);
                    // 重新设置边框样式
                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress4,
                    // sheet, wb);
                }
                columnIndex = 3;
                cell = row.createCell(columnIndex);
                cell.setCellValue("雨污相关性");
                cell.setCellStyle(cellStyle);
                cell = row.createCell(++columnIndex);
                cell.setCellValue(rainSewageTotal.intValue());
                cell.setCellStyle(cellStyle);
                if (rainSewageTotal.intValue() > 0) {
                    // 4列后
                    List<AnalyseMsgListVo> analyseData = rainSewageData.getAnalyseData();
                    if (analyseData != null && analyseData.size() > 0) {
                        for (int i = 0; i < analyseData.size(); i++) {
                            AnalyseMsgListVo analyseMsgListVo = analyseData.get(i);
                            String stationName = analyseMsgListVo.getStationName();
                            List<AnalyseRecordVo> resMsg = analyseMsgListVo.getResMsg();
                            // 站点名称 大于1的情况
                            if (resMsg != null && resMsg.size() > 0) {
                                // 合并 单元格
                                // 站点名称 包含开始点 应该+1

                                if (i != 0) {
                                    rowIndex = rowIndex + 1;
                                }
                                row = sheet.getRow(rowIndex);
                                columnIndex = 5;
                                if (stationName == null) {
                                    stationName = "";
                                }
                                cell = row.createCell(columnIndex);
                                cell.setCellValue(stationName);
                                cell.setCellStyle(cellStyle);
                                cell = row.createCell(++columnIndex);
                                cell.setCellValue(resMsg.size());
                                cell.setCellStyle(cellStyle);
                                if (resMsg.size() > 1) {
                                    CellRangeAddress callRangeAddress5 = new CellRangeAddress(rowIndex,
                                            resMsg.size() + rowIndex - 1, 5, 5);
                                    sheet.addMergedRegion(callRangeAddress5);
                                    // 重新设置边框样式
                                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress5,
                                    // sheet,
                                    // wb);
                                    // 次数
                                    CellRangeAddress callRangeAddress6 = new CellRangeAddress(rowIndex,
                                            resMsg.size() + rowIndex - 1, 6, 6);
                                    sheet.addMergedRegion(callRangeAddress6);
                                    // 重新设置边框样式
                                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress6,
                                    // sheet,
                                    // wb);
                                }

                                /**
                                 * 两种情况 1 三大类 第一个站点的 第一个相关性 站点 2 其他站点的
                                 */
                                for (int j = 0; j < resMsg.size(); j++) {
                                    // 除开第一个站点的第一个时间 其余都新增行数
                                    if (j != 0) {
                                        rowIndex++;
                                    }

                                    // row = sheet.createRow(rowIndex);
                                    row = sheet.getRow(rowIndex);
                                    if (row == null) {
                                        row = sheet.createRow(rowIndex);
                                    }
                                    AnalyseRecordVo analyseRecordVo = resMsg.get(j);
                                    String stationName2 = analyseRecordVo.getStationName();
                                    if (stationName2 == null) {
                                        stationName2 = "";
                                    }
                                    String date = analyseRecordVo.getDate();
                                    if (date == null) {
                                        date = "";
                                    }
                                    columnIndex = 7;
                                    // 相关站点
                                    row.createCell(columnIndex).setCellValue(stationName2);
                                    // 日期
                                    row.createCell(++columnIndex).setCellValue(date);
                                    if (rowIndex >= total) {
                                        break;
                                    }

                                }

                            }

                        }

                    }

                }

            }
            // 雨河
            if (rainRiverData != null) {
                // 雨污河连通第一行
                rowIndex++;
                // row = sheet.createRow(rowIndex);
                row = sheet.getRow(rowIndex);
                // 合并单元格行
                if (rainRiverTotal.intValue() > 0) {
                    // 雨污河连通
                    CellRangeAddress callRangeAddress3 = new CellRangeAddress(rowIndex,
                            rainRiverTotal.intValue() + rowIndex - 1, 3, 3);
                    sheet.addMergedRegion(callRangeAddress3);
                    // 重新设置边框样式
                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress3,
                    // sheet, wb);
                    // 数目
                    CellRangeAddress callRangeAddress4 = new CellRangeAddress(rowIndex,
                            rainRiverTotal.intValue() + rowIndex - 1, 4, 4);
                    sheet.addMergedRegion(callRangeAddress4);
                    // 重新设置边框样式
                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress4,
                    // sheet, wb);
                }
                columnIndex = 3;
                cell = row.createCell(columnIndex);
                cell.setCellValue("雨污河连通");
                cell.setCellStyle(cellStyle);
                cell = row.createCell(++columnIndex);
                cell.setCellValue(rainRiverTotal.intValue());
                cell.setCellStyle(cellStyle);

                if (rainRiverTotal.intValue() > 0) {
                    // 4列后
                    List<AnalyseMsgListVo> analyseData = rainRiverData.getAnalyseData();
                    if (analyseData != null && analyseData.size() > 0) {
                        for (int i = 0; i < analyseData.size(); i++) {
                            AnalyseMsgListVo analyseMsgListVo = analyseData.get(i);
                            String stationName = analyseMsgListVo.getStationName();
                            List<AnalyseRecordVo> resMsg = analyseMsgListVo.getResMsg();
                            // 站点名称 大于1的情况
                            if (resMsg != null && resMsg.size() > 0) {
                                // 合并 单元格
                                // 站点名称 包含开始点 应该+1
                                if (i != 0) {
                                    rowIndex = rowIndex + 1;
                                }
                                if (resMsg.size() > 1) {
                                    CellRangeAddress callRangeAddress5 = new CellRangeAddress(rowIndex,
                                            resMsg.size() + rowIndex - 1, 5, 5);
                                    sheet.addMergedRegion(callRangeAddress5);
                                    // 重新设置边框样式
                                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress5,
                                    // sheet,
                                    // wb);
                                    // 次数
                                    CellRangeAddress callRangeAddress6 = new CellRangeAddress(rowIndex,
                                            resMsg.size() + rowIndex - 1, 6, 6);
                                    sheet.addMergedRegion(callRangeAddress6);
                                    // 重新设置边框样式
                                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress6,
                                    // sheet,
                                    // wb);
                                }
                                if (stationName == null) {
                                    stationName = "";
                                }
                                row = sheet.getRow(rowIndex);
                                columnIndex = 5;
                                // 站点
                                cell = row.createCell(columnIndex);
                                cell.setCellValue(stationName);
                                cell.setCellStyle(cellStyle);
                                // 次数
                                cell = row.createCell(++columnIndex);
                                cell.setCellValue(resMsg.size());
                                cell.setCellStyle(cellStyle);
                                for (int j = 0; j < resMsg.size(); j++) {
                                    if (j != 0) {
                                        rowIndex++;
                                    }
                                    // row = sheet.createRow(rowIndex);
                                    row = sheet.getRow(rowIndex);
                                    AnalyseRecordVo analyseRecordVo = resMsg.get(j);
                                    String stationName2 = analyseRecordVo.getStationName();
                                    if (stationName2 == null) {
                                        stationName2 = "";
                                    }
                                    String date = analyseRecordVo.getDate();
                                    if (date == null) {
                                        date = "";
                                    }
                                    columnIndex = 7;
                                    // 相关站点
                                    row.createCell(columnIndex).setCellValue(stationName2);
                                    // 日期
                                    row.createCell(++columnIndex).setCellValue(date);
                                    if (rowIndex >= total) {
                                        break;
                                    }

                                }

                            }

                        }
                    }

                }

            }
            // 管道
            if (pipeLineData != null) {
                // 管道堵塞风险第一行
                rowIndex++;
                // row = sheet.createRow(rowIndex);
                row = sheet.getRow(rowIndex);
                // 大于最大值
                if (total >= 65530) {
                    // 合并单元格行
                    // 雨污河连通
                    CellRangeAddress callRangeAddress3 = new CellRangeAddress(rowIndex, 65530, 3, 3);
                    sheet.addMergedRegion(callRangeAddress3);
                    // 重新设置边框样式
                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress3,
                    // sheet, wb);
                    // 数目
                    CellRangeAddress callRangeAddress4 = new CellRangeAddress(rowIndex, 65530, 4, 4);
                    sheet.addMergedRegion(callRangeAddress4);
                    // 重新设置边框样式
                    // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress4,
                    // sheet, wb);
                } else {
                    if (pipeTotal.intValue() > 0) {
                        // 合并单元格行
                        // 雨污河连通
                        CellRangeAddress callRangeAddress3 = new CellRangeAddress(rowIndex,
                                pipeTotal.intValue() + rowIndex - 1, 3, 3);
                        sheet.addMergedRegion(callRangeAddress3);
                        // 重新设置边框样式
                        // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress3,
                        // sheet, wb);
                        // 数目
                        CellRangeAddress callRangeAddress4 = new CellRangeAddress(rowIndex,
                                pipeTotal.intValue() + rowIndex - 1, 4, 4);
                        sheet.addMergedRegion(callRangeAddress4);
                        // 重新设置边框样式
                        // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress4,
                        // sheet, wb);
                    }
                }

                columnIndex = 3;
                cell = row.createCell(columnIndex);
                cell.setCellValue("管道堵塞风险");
                cell.setCellStyle(cellStyle);
                cell = row.createCell(++columnIndex);
                cell.setCellValue(pipeTotal.intValue());
                cell.setCellStyle(cellStyle);
                if (pipeTotal.intValue() > 0) {
                    // 4列后
                    List<AnalyseMsgListVo> analyseData = pipeLineData.getAnalyseData();
                    if (analyseData != null && analyseData.size() > 0) {
                        for (int i = 0; i < analyseData.size(); i++) {
                            AnalyseMsgListVo analyseMsgListVo = analyseData.get(i);
                            String stationName = analyseMsgListVo.getStationName();
                            List<AnalyseRecordVo> resMsg = analyseMsgListVo.getResMsg();

                            // 站点名称 大于1的情况
                            if (resMsg != null && resMsg.size() > 0) {
                                // 合并 单元格
                                // 站点名称 包含开始点 应该+1
                                if (i != 0) {
                                    rowIndex = rowIndex + 1;
                                }
                                if (resMsg.size() + rowIndex < 65530) {
                                    if (resMsg.size() > 1) {
                                        CellRangeAddress callRangeAddress5 = new CellRangeAddress(rowIndex,
                                                resMsg.size() + rowIndex - 1, 5, 5);
                                        sheet.addMergedRegion(callRangeAddress5);
                                        // 重新设置边框样式
                                        // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress5,
                                        // sheet, wb);
                                        // 次数
                                        CellRangeAddress callRangeAddress6 = new CellRangeAddress(rowIndex,
                                                resMsg.size() + rowIndex - 1, 6, 6);
                                        sheet.addMergedRegion(callRangeAddress6);
                                        // 重新设置边框样式
                                        // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress6,
                                        // sheet, wb);
                                    }

                                } else {
                                    if (rowIndex < 65530) {
                                        CellRangeAddress callRangeAddress5 = new CellRangeAddress(rowIndex, 65530, 5,
                                                5);
                                        sheet.addMergedRegion(callRangeAddress5);
                                        // 重新设置边框样式
                                        // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress5,
                                        // sheet, wb);
                                        // 次数
                                        CellRangeAddress callRangeAddress6 = new CellRangeAddress(rowIndex, 65530, 6,
                                                6);
                                        sheet.addMergedRegion(callRangeAddress6);
                                        // 重新设置边框样式
                                        // RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, callRangeAddress6,
                                        // sheet, wb);
                                    } else {
                                        break;
                                    }
                                }

                                if (stationName == null) {
                                    stationName = "";
                                }
                                columnIndex = 5;
                                row = sheet.getRow(rowIndex);
                                // 站点
                                cell = row.createCell(columnIndex);
                                cell.setCellValue(stationName);
                                cell.setCellStyle(cellStyle);
                                // 次数
                                cell = row.createCell(++columnIndex);
                                cell.setCellValue(resMsg.size());
                                cell.setCellStyle(cellStyle);
                                for (int j = 0; j < resMsg.size(); j++) {
                                    if (j != 0) {
                                        rowIndex++;
                                    }
                                    // row = sheet.createRow(rowIndex);
                                    row = sheet.getRow(rowIndex);
                                    if (row == null) {
                                        row = sheet.createRow(rowIndex);
                                    }
                                    AnalyseRecordVo analyseRecordVo = resMsg.get(j);
                                    String stationName2 = analyseRecordVo.getStationName();
                                    if (stationName2 == null) {
                                        stationName2 = "";
                                    }
                                    String date = analyseRecordVo.getDate();
                                    if (date == null) {
                                        date = "";
                                    }
                                    columnIndex = 7;
                                    // 相关站点
                                    row.createCell(columnIndex).setCellValue(stationName2);
                                    // 日期
                                    row.createCell(++columnIndex).setCellValue(date);
                                    // System.out.println(row.toString());
                                    if (rowIndex >= total) {
                                        break;
                                    }
                                }
                            }
                        }

                    }

                }
            }
            sheet.setDefaultRowHeight((short) (16.5 * 20));
            // 列宽自适应 有中文不适用
            // for (int i = 0; i <= 7; i++) {
            // sheet.autoSizeColumn(i);
            // }

            String title = "czjl_all";
            response.setHeader("Content-disposition", "attachment;fileName=" + title + ".xls");
            response.setContentType("application/octet-stream;charset=utf-8");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
