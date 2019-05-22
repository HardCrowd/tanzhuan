/**
 * 
 */
package com.ecxls.integral.util.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ecxls.integral.util.exception.EcxlsException;

/**
 * <p>ClassName: ExcelHelp<p>
 * <p>Description: Poi操作Excel帮助类<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年5月31日 下午3:07:58
 */
public class ExcelHelp {
	
	//最大行数，按0开始至65535
	private int maxRow = 65535;
	//最大列数，按0开始至256
	private int maxCell = 256;

	/**
	 * 按对象参数，获取sheet对象 		
	 * @param sheet	可以是sheetName OR sheetIndex
	 * @return 一个工作表
	 */
	public HSSFSheet getSheet(HSSFWorkbook workbook,Object sheet){
		if(sheet instanceof String){
			return workbook.getSheet(String.valueOf(sheet));
		}else if(sheet instanceof Integer){
			return workbook.getSheetAt(Integer.valueOf(sheet.toString()));
		}else{
			return null;
		}
	}
	
	/**
	 * 拷贝外部传入样式
	 * @param style	外部设置的样式
	 * @return	内部工作簿创建的样式对象
	 */
	public HSSFCellStyle copyCellStyle(HSSFWorkbook workbook, HSSFCellStyle style){
		HSSFCellStyle copyCellStyle = workbook.createCellStyle();
		copyCellStyle.cloneStyleFrom(style);
		return copyCellStyle;
	}
	
	/**
	 * 包含文件路径的copySheet方法 		
	 * @param path			文件路径
	 * @param source		源
	 * @param target		目标
	 * @return true：成功 false：失败
	 * @throws IOException
	 * @throws EcxlsException 
	 */
	public boolean copySheet(HSSFWorkbook workbook,Object source,Object target) throws EcxlsException{
		//源sheet
		HSSFSheet sourceSheet = this.getSheet(workbook,source);
		//目标sheet
		HSSFSheet targetSheet = this.getSheet(workbook,target);
		//判断是否都存在
		if(null != sourceSheet && null != targetSheet){
			String sourceSheetName = sourceSheet.getSheetName();
			String targetSheetName = targetSheet.getSheetName();
			//如果sourceSheetName与targetSheetNum所在sheet名称一致，则操作无意义，返回false
			if(sourceSheetName.equals(targetSheetName)){
				return false;
			}
			//获取有效行数，即工作簿有内容
			int physicalNumberOfRows = sourceSheet.getPhysicalNumberOfRows(); 
			//如果没有实际内容，则不做处理，返回false
			if(physicalNumberOfRows <= 0){
				return false;
			}
			this.copySheet(workbook,sourceSheet,targetSheet);
			return true;
		}
		return false;
	}
	
	/**
	 * 公共copySheet
	 * @param workbook		工作簿
	 * @param sourceSheet	源sheet
	 * @param targetSheet	目标sheet
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public void copySheet(HSSFWorkbook workbook,HSSFSheet sourceSheet,HSSFSheet targetSheet){
		
		//最大列数
		int maxColumnNum = 0;
		// 用于复制注释
		HSSFPatriarch targetPatriarch = targetSheet.createDrawingPatriarch();
		//复制row
		
		//第一行
		int firstRowNum = sourceSheet.getFirstRowNum(); 
		//最后一行的行数，返回值会比实际行数小1，所以这里要加1
		int lastRowNum = (sourceSheet.getLastRowNum() + 1); 
		for (int i = firstRowNum; i < lastRowNum; i++) {
			
			HSSFRow sourceRow = sourceSheet.getRow(i);
			HSSFRow targetRow = targetSheet.createRow(i);
			
			if(null != sourceRow){
				//设置行个高
				targetRow.setHeight(sourceRow.getHeight());
				//第一列
				short firstCellNum = sourceRow.getFirstCellNum();
				//获取列数，比最后一列列标大1
				short lastCellNum = sourceRow.getLastCellNum();
				
				//设置最大列数
				if (lastCellNum > maxColumnNum) {
					maxColumnNum = lastCellNum;
				}
				
				for (int j = firstCellNum; j < lastCellNum; j++) {
					
					HSSFCell sourceCell = sourceRow.getCell(j);
					HSSFCell targetCell = targetRow.getCell(j);
					
					if(null != sourceCell){
						
						// 拷贝单元格，包括内容和样式
						if(null == targetCell){
							targetCell = targetRow.createCell(j);
						}
						
						// 处理单元格样式
						targetCell.setCellStyle(sourceCell.getCellStyle());
						
						// 处理单元格内容
						switch (sourceCell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								targetCell.setCellValue(sourceCell.getRichStringCellValue());
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								targetCell.setCellValue(sourceCell.getNumericCellValue());
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								targetCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								targetCell.setCellValue(sourceCell.getBooleanCellValue());
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								targetCell.setCellFormula(sourceCell.getCellFormula());
								break;
							default:
								break;
						}
						
						//获取原有注释
						HSSFComment targetCellComment = targetCell.getCellComment();
						//如果有则删除，不然会报错
						if(null != targetCellComment){
							targetCell.removeCellComment();
						}
						// 处理单元格注释
						HSSFComment comment = sourceCell.getCellComment();
						if (comment != null) {
							HSSFComment newComment = targetPatriarch.createCellComment(new HSSFClientAnchor());
							newComment.setAuthor(comment.getAuthor());
							newComment.setColumn(comment.getColumn());
							newComment.setFillColor(comment.getFillColor());
							newComment.setHorizontalAlignment(comment.getHorizontalAlignment());
							newComment.setLineStyle(comment.getLineStyle());
							newComment.setLineStyleColor(comment.getLineStyleColor());
							newComment.setLineWidth(comment.getLineWidth());
							newComment.setMarginBottom(comment.getMarginBottom());
							newComment.setMarginLeft(comment.getMarginLeft());
							newComment.setMarginTop(comment.getMarginTop());
							newComment.setMarginRight(comment.getMarginRight());
							newComment.setNoFill(comment.isNoFill());
							newComment.setRow(comment.getRow());
							newComment.setString(comment.getString());
							newComment.setVerticalAlignment(comment.getVerticalAlignment());
							newComment.setVisible(comment.isVisible());
							targetCell.setCellComment(newComment);
						}
					}
				}
			}
		}
		
		//复制图片内容 （ 目前excel内置形状不能复制）
		List<HSSFPictureData> allPictures = workbook.getAllPictures();
		if(allPictures.size() > 0){
			for (HSSFShape shape : sourceSheet.getDrawingPatriarch().getChildren()) {
				HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();  
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;  
                    int pictureIndex = pic.getPictureIndex() - 1;  
                    HSSFPictureData hssfPictureData = allPictures.get(pictureIndex);
                    //addPicture内参数为1：图片byte[]数据，2：图片类型
                    targetPatriarch.createPicture(anchor, workbook.addPicture(hssfPictureData.getData(), hssfPictureData.getFormat()));
                }
			}
		}
		
		//复制合并单元格内容
		int numMergedRegions = sourceSheet.getNumMergedRegions();
		for (int i = 0; i < numMergedRegions; i++) {
			targetSheet.addMergedRegion(sourceSheet.getMergedRegion(i));
		}
		
		//设置列宽
		for (int i = 0; i < maxColumnNum; i++) {
			targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
		}
		
	}
	
	
	
	/**
	 * 修改sheet名称
	 * @param workbook		工作薄对象
	 * @param sheetObj		sheet标示(名称or下标)
	 * @param newSheetName	新的sheet名称
	 * @return	
	 * @throws EcxlsException
	 */
	public boolean renameSheet(HSSFWorkbook workbook,Object sheetObj,String newSheetName) throws EcxlsException{
		HSSFSheet sheet = this.getSheet(workbook,sheetObj);
		if(null == sheet){
			return false;
		}else{
			int sheetIndex = workbook.getSheetIndex(sheet);
			workbook.setSheetName(sheetIndex, newSheetName);
		}
		return true;
	}
	
	/**
	 * 删除sheet
	 * @param workbook		HSSFWorkbook
	 * @param sheetObj		sheet标识
	 * @return
	 * @throws EcxlsException
	 */
	public boolean removeSheet(HSSFWorkbook workbook,Object sheetObj) throws EcxlsException{
		try {
			int numberOfSheets = workbook.getNumberOfSheets();
			//如果sheets只有一个，则不能做删除操作，workbook中必须保留至少一个sheet，不然会造成文件损坏
			if(numberOfSheets == 1){
				return false;
			}
			HSSFSheet sheet = this.getSheet(workbook,sheetObj);
			if(null == sheet){
				return false;
			}
			workbook.removeSheetAt(workbook.getSheetIndex(sheet));
			return true;
		} catch (Exception e) {
			throw new EcxlsException(e);
		} 
	}
	
	/**
	 * 
	 * 删除行
	 * @param workbook		工作薄对象
	 * @param sheet			sheet标识
	 * @param rowNum		行数
	 * @return
	 * @throws EcxlsException
	 */
	public boolean removeRow(HSSFWorkbook workbook,Object sheet,int rowNum) throws EcxlsException{
		HSSFSheet sheet2 = this.getSheet(workbook,sheet);
		int lastRowNum = sheet2.getLastRowNum();
		//如果sheet中没有有效的行，则返回false
		if(lastRowNum == 0){
			return false;
		}
		//如果rowNum大于或等于lastRowNum，则返回false
		if(rowNum >= lastRowNum){
			return false;
		}
		//删除算法，参数1为起始行，参数2为结束行，参数3为移动方向-->正整数为向下移动n行，负整数为向下移动n行
		sheet2.shiftRows((rowNum + 1), lastRowNum, -1);
		return true;
	}
	
	/**
	 * 删除列
	 * @param workbook		HSSFWorkbook
	 * @param sheet			sheet标识
	 * @param cellNum		列数
	 * @return
	 * @throws EcxlsException
	 */
	public boolean removeCell(HSSFWorkbook workbook, Object sheet, int cellNum) throws EcxlsException{
		HSSFSheet sheet2 = this.getSheet(workbook,sheet);
		if(null == sheet2){
			return false;
		}
		//遍历所有行
		for (Iterator<Row> rowIterator = sheet2.rowIterator(); rowIterator.hasNext();) {
			//行
			HSSFRow row = (HSSFRow) rowIterator.next();
			//列
			HSSFCell cell = row.getCell(cellNum);
			if(null == cell){
				continue;
			}
			//删除内容
			row.removeCell(cell);
			//获取最后一列下标
			short lastCellNum = row.getLastCellNum();
			for (int i = cellNum; i < lastCellNum; i++) {
				//单元格宽度
				int columnWidth = sheet2.getColumnWidth(i + 1);
			    //删除列的下一列
	            HSSFCell cell2 = row.getCell(i + 1);
		        
	            if(null == cell2){
	            	break;
	            }
	            //设置删除单元格的宽度
	            sheet2.setColumnWidth(i, columnWidth);
	            //移动列
	            row.moveCell(cell2, (short)i);
			}
		}
		return true;
	}
	
	/**
	 * 在excel上，单元格值和样式<p>
	 * @author chenluning		
	 * @param workbook		工作薄对象
	 * @param sheetObj		sheet标识
	 * @param rowNum		行号
	 * @param cellNum		列号
	 * @param value			值
	 * @param style			样式
	 * @return
	 * @throws EcxlsException
	 */
	public boolean insertCell(HSSFWorkbook workbook,Object sheetObj,int rowNum, int cellNum,String value,
			HSSFCellStyle style) throws EcxlsException{
		HSSFSheet sheet = this.getSheet(workbook,sheetObj);
		HSSFRow row = sheet.getRow(rowNum);
		//如果为空白行，则新建一行
		if(null == row){
			row = sheet.createRow(rowNum);
		}
		//如果为空白列，则新建一列
		HSSFCell cell = row.getCell(cellNum);
		if(null == cell){
			cell = row.createCell(cellNum);
		}
		cell.setCellValue(value);
		
		//设置单元格样式
		if(null != style){
			cell.setCellStyle(this.copyCellStyle(workbook,style));
		}
		//设置列自适应内容宽度
		sheet.autoSizeColumn(cellNum);
		return true;
	}
	
	/**
	 * 根据sheet码，行号,起始列号,样式给指定excel设定该行各列值和样式<p>
	 * @author chenluning		
	 * @param workbook			工作薄对象
	 * @param sheetObj			sheet标识
	 * @param rowNum			行号
	 * @param startCellNum		还是列数
	 * @param style				样式
	 * @param values			值
	 * @return
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook,Object sheetObj,int rowNum, int startCellNum,
			HSSFCellStyle style,Object values) throws EcxlsException{
		HSSFSheet sheet = this.getSheet(workbook,sheetObj);
		HSSFRow row = sheet.getRow(rowNum);
		//如果为空白行，则新建一行
		if(null == row){
			row = sheet.createRow(rowNum);
		}
		//样式
		HSSFCellStyle initCellStyle = null;
		//设置单元格样式
		if(null != style){
			initCellStyle = this.copyCellStyle(workbook, style);
		}
		//如果参数startCellNum，默认从0开始
		if(values instanceof String[]){
			String[] strValues = (String[])values;
			if(null != strValues && strValues.length > 0){
				int len = strValues.length;
				if(len > maxCell){
					return false;
				}
				for (int i = 0; i < len; i++) {
					HSSFCell cell = row.createCell((i+startCellNum));
					cell.setCellValue(strValues[i]);
					if(null != initCellStyle){
						cell.setCellStyle(initCellStyle);
					}
				}
				strValues = null;
			}
		}else if(values instanceof Map){
			@SuppressWarnings("unchecked")
			Map<String, String> mapValues = (Map<String,String>)values;
			if(null != mapValues && mapValues.size() > 0){
				int size = mapValues.size();
				if(size > maxCell){
					return false;
				}
				int i = 0;
				Set<String> keySet = mapValues.keySet();
				for (String key : keySet) {
					HSSFCell cell = row.createCell((i+startCellNum));
					cell.setCellValue(mapValues.get(key));
					if(null != initCellStyle){
						cell.setCellStyle(initCellStyle);
					}
					i++;
				}
				mapValues = null;
			}
		}
		return true;
	}
	
	/**
	 * 获取单元格值
	 * @createTime:2018年10月26日 下午2:06:02
	 * @param cell 单元格
	 * @return 转换object对象返回
	 */
	@SuppressWarnings("deprecation")
	public Object getCellValue(Cell cell){
		Object obj = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				obj = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				double numericCellValue = cell.getNumericCellValue();
				//poi会把日期类型分到double下面，这里做个判断并分类取值
				if ( DateUtil.isValidExcelDate(numericCellValue) ) {  
		            CellStyle style = cell.getCellStyle();  
		            if(null == style) {
		            	obj = numericCellValue;
		            }else{
		            	int i = style.getDataFormat();  
		            	String f = style.getDataFormatString();  
		            	if(HSSFDateUtil.isADateFormat(i, f)){
		            		obj = cell.getDateCellValue();
		            	}else{
		            		obj = numericCellValue;
		            	}
		            }
		        }
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				obj = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				obj = null;
				break;
			case Cell.CELL_TYPE_FORMULA:
				obj = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_ERROR:
				obj = cell.getErrorCellValue();
				break;
			default:
				break;
		}
		return obj;
	}
	
	/**
	 * 获取单元格值
	 * @author chenluning		
	 * @param workbook			工作薄对象
	 * @param sheetObj			sheet标识
	 * @param startRow			开始行
	 * @param endRow			结束行
	 * @param startCell			开始列
	 * @param endCell			结束列
	 * @return
	 * @throws EcxlsException
	 */
	public List<Object> getDatas(HSSFWorkbook workbook,Object sheetObj,int startRow, int endRow,int startCell,int endCell) throws EcxlsException{
		List<Object> list = new ArrayList<Object>();
		HSSFSheet sheet = this.getSheet(workbook,sheetObj);
		//参数endRow为-1，返回所有内容
		if(endRow == -1){
			for (Row row : sheet) {
				for (Cell cell : row) {
					list.add(this.getCellValue(cell));
				}
			}
		}else if(endRow > startRow && endRow < this.maxRow && endCell > startCell && endCell < this.maxCell){
			HSSFRow row;
			HSSFCell cell;
			for (int i = startRow; i <= endRow; i++) {
				row = sheet.getRow(i);
				if(null == row){
					continue;
				}
				for (int j = startCell; j <= endCell; j++) {
					cell = row.getCell(j);
					if(null == cell){
						continue;
					}
					list.add(this.getCellValue(cell));
				}
			}
		}
		return list;
	}
	

	/**
	 * 
	 * 在excel上，根据sheet名称清除该sheet中所有内容<p>
	 * @author chenluning		
	 * @param workbook
	 * @param sheetObj
	 * @return
	 * @throws EcxlsException
	 */
	public boolean clearSheet(HSSFWorkbook workbook, Object sheetObj) throws EcxlsException{
		HSSFSheet sheet = this.getSheet(workbook,sheetObj);
		//删除原sheet，新建sheet，修改名称，变更出现顺位
		String sheetName = sheet.getSheetName();
		int sheetIndex = workbook.getSheetIndex(sheet);
		workbook.removeSheetAt(sheetIndex);
		workbook.createSheet(sheetName);
		workbook.setSheetOrder(sheetName, sheetIndex);
		return true;
	}
	
	/**
	 * 
	 * 根据sheet码和起始行统计指定excel中该sheet中有效行数<p>
	 * @author chenluning		
	 * @param workbook		工作薄对象
	 * @param sheetObj		sheet标识
	 * @param startRow		起始行
	 * @return
	 * @throws EcxlsException
	 */
	public int countRows(HSSFWorkbook workbook,Object sheetObj,int startRow) throws EcxlsException{
		int countRows = this.countRows(workbook, sheetObj);
		int count = 0;
		if(countRows > 0){
			HSSFSheet sheet = this.getSheet(workbook,sheetObj);
			int lastRowNum = sheet.getLastRowNum() + 1;
			HSSFRow row;
			for (int i = startRow; i < lastRowNum; i++) {
				row = sheet.getRow(i);
				if(null != row){
					count ++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 
	 * 根据sheet码统计指定excel中该sheet中有效行数
	 * @author chenluning		
	 * @param workbook		工作薄对象
	 * @param sheetObj		sheet标识
	 * @return
	 * @throws EcxlsException
	 */
	public int countRows(HSSFWorkbook workbook,Object sheetObj) throws EcxlsException{
		return this.getSheet(workbook,sheetObj).getPhysicalNumberOfRows();
	}
	
	/**
	 * 
	 * 在excel上， 根据sheet名称、行号、样式对象设置该行样式
	 * @author chenluning		
	 * @param workbook
	 * @param sheetObj
	 * @param rowIndex
	 * @param style
	 * @throws EcxlsException
	 */
	public void setRowStyle(HSSFWorkbook workbook ,Object sheetObj,int rowIndex,
			HSSFCellStyle style) throws EcxlsException{
		this.setCellStyle(workbook, sheetObj, rowIndex, 0, style);
	}
	
	/**
	 * 
	 * 在默认的excel上， 根据sheet名称、行号、列号、样式对象设置该行样式
	 * @author chenluning		
	 * @param workbook
	 * @param sheetObj
	 * @param rowIndex
	 * @param cellIndex
	 * @param style
	 * @throws EcxlsException
	 */
	public void setCellStyle(HSSFWorkbook workbook, Object sheetObj, int rowIndex,
			int cellIndex,HSSFCellStyle style) throws EcxlsException{
		HSSFCellStyle initCellStyle = this.copyCellStyle(workbook, style);
		HSSFSheet sheet = this.getSheet(workbook,sheetObj);
		HSSFRow row = sheet.getRow(rowIndex);
		if(null == row){
			row = sheet.createRow(rowIndex);
		}
		HSSFCell cell;
		for (int i = cellIndex; i < this.maxCell; i++) {
			cell = row.getCell(i);
			if(null == cell){
				cell = row.createCell(i);
			}
			cell.setCellStyle(initCellStyle);
		}
	}
	
	/**
	 * 
	 * 在excel上，根据sheet名称、开始行、结束行、开始列、结束列合并单元格
	 * @author chenluning		
	 * @param workbook
	 * @param sheetObj
	 * @param startRow
	 * @param endRow
	 * @param startCell
	 * @param endCell
	 * @param style
	 * @throws EcxlsException
	 */
	public void setMergeCell(HSSFWorkbook workbook, Object sheetObj, int startRow,int endRow,int startCell,int endCell,
			HSSFCellStyle style) throws EcxlsException{
		HSSFSheet sheet = this.getSheet(workbook, sheetObj);
		sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, startCell, endCell));
		if(null != style){
			HSSFCellStyle initCellStyle = this.copyCellStyle(workbook, style);
			HSSFRow row;
			HSSFCell cell;
			for (int i = startRow; i <= endRow; i++) {
				row = sheet.getRow(i);
				if(null == row){
					row = sheet.createRow(i);
				}
				for (int j = startCell; j <= endCell; j++) {
					cell = row.getCell(j);
					if(null == cell){
						cell = row.createCell(j);
					}
					cell.setCellStyle(initCellStyle);
				}
			}
		}
	}
}
