package com.ecxls.integral.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ecxls.integral.util.Formula;
import com.ecxls.integral.util.exception.EcxlsException;

/**
 * <p>ClassName: ExcelAbstract<p>
 * <p>Description: excel 处理<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年5月31日 下午3:07:45
 */
public abstract class ExcelUtil {
	
	//工作簿
	private HSSFWorkbook workbook;
	//默认sheet名称
	private String defaultSheetName = "Sheet1";
	//文件路径
	private String path;
	//excelhelp
	private ExcelHelp excelHelp = new ExcelHelp();
	
	/**
	 * 无参数构造方法
	 */
	public ExcelUtil(){}
	
	/**
	 * 有参数构造方法，初始化工作薄
	 * @param path		excel文件路径
	 * @throws EcxlsException
	 */
	public ExcelUtil(String path) throws EcxlsException{
		this.path = path;
		this.init();
	}

	/**
	 * 初始化
	 * @throws EcxlsException
	 */
	public void init() throws EcxlsException{
		this.workbook = this.initWorkBook(new File(this.path));
	}
	
	/**
	 * 
	 * @MethodName initWorkBook
	 * 初始化工作簿
	 * @param file	文件对象
	 * @throws EcxlsException 
	 */
	public HSSFWorkbook initWorkBook(File file) throws EcxlsException{
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			return new HSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			throw new EcxlsException(e);
		} finally {
			Formula.closeIO(fileInputStream);
		}
	}
	
	/**
	 * 
	 * @MethodName: writeWorkBook<p>
	 * 输出默认工作簿，并关闭文件输出流 <p>
	 * @author chenluning		
	 * @throws EcxlsException
	 */
	public void writeWorkBook() throws EcxlsException{
		this.writeWorkBook(this.workbook, this.path);
	}
	
	/**
	 * 
	 * @MethodName: writeWorkBook<p>
	 * 输出工作簿，并关闭文件输出流 	<p>
	 * @author chenluning		
	 * @param workbook		工作薄对象	
	 * @param path			文件路径
	 * @throws EcxlsException
	 */
	private void writeWorkBook(HSSFWorkbook workbook, String path) throws EcxlsException{
		this.writeWorkBook(workbook, new File(path));
	}
	
	/**
	 * 
	 * @MethodName writeWorkBook
	 * 输出工作簿，并关闭文件输出流 	
	 * @param workbook	工作薄对象	
	 * @param file 		输出文件
	 * @throws EcxlsException
	 */
	public void writeWorkBook(HSSFWorkbook workbook, File file) throws EcxlsException{
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			throw new EcxlsException(e);
		} finally {
			Formula.closeIO(fileOutputStream);
		}
	}
	
	/**
	 * @MethodName copySheet
	 * 在默认excel上，通过源sheet名称和目标sheet码获得源sheet对象和目标sheet对象，把源sheet中的内容复制到目标码的sheet上
	 * @author chenluning
	 * @param sourceSheetName		源sheet名称
	 * @param targetSheetNum		目标sheet下标
	 * @return true：成功 false：失败
	 * @throws EcxlsException 
	 */
	public boolean copySheet(String sourceSheetName, int targetSheetNum) throws EcxlsException {
		return this.excelHelp.copySheet(this.workbook,sourceSheetName, targetSheetNum);
	}
	/**
	 * @MethodName copySheet
	 * 在默认excel上，通过源sheet名称和目标sheet名称获得源sheet对象和目标sheet对象，通过源sheet名称获得源sheet对象，把源sheet的内容复制到目标sheet上
	 * @author chenluning
	 * @param sourceSheetName	源sheet名称
	 * @param targetSheetName	目标sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException 
	 */
	public boolean copySheet(String sourceSheetName, String targetSheetName) throws EcxlsException {
		return this.excelHelp.copySheet(this.workbook,sourceSheetName, targetSheetName);
	}
	/**
	 * @MethodName copySheet
	 * 在默认的excel上，通过源sheet码和目标sheet码获得源sheet对象和目标sheet对象，把源sheet上的内容复制到目标sheet上
	 * @author chenluning
	 * @param sourceSheetNum	源sheet码
	 * @param targetSheetNum	目标sheet码
	 * @return true：成功， false：失败
	 * @throws EcxlsException 
	 */
	public boolean copySheet(int sourceSheetNum, int targetSheetNum) throws EcxlsException {
		return this.excelHelp.copySheet(this.workbook,sourceSheetNum, targetSheetNum);
	}
	/**
	 * @MethodName copySheet
	 * 在默认的excel上，通过源sheet码和目标sheet名称获得源sheet对象和目标sheet对象，把源sheet上的内容复制到目标sheet上
	 * @author chenluning
	 * @param sourceSheetNum	源sheet码
	 * @param targetSheetName	目标sheet码
	 * @return true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean copySheet(int sourceSheetNum, String targetSheetName)throws EcxlsException {
		return this.excelHelp.copySheet(this.workbook,sourceSheetNum, targetSheetName);
	}
	/**
	 * @MethodName copySheet
	 * 通过源sheet名称和目标sheet码获得源sheet对象和目标sheet对象，把指定excel的源sheet上的内容复制到目标sheet上
	 * @author chenluning
	 * @param workbook				HSSFWorkbook
	 * @param sourceSheetName		源sheet名称
	 * @param targetSheetNum		目标sheet码
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean copySheet(HSSFWorkbook workbook, String sourceSheetName,int targetSheetNum) throws EcxlsException {
		return this.excelHelp.copySheet(workbook, sourceSheetName, targetSheetNum);
	}
	/**
	 * @MethodName copySheet
	 * 通过源sheet名称和目标sheet名称获得源sheet对象和目标sheet对象，把指定excel的源sheet上的内容复制到目标sheet上
	 * @author chenluning
	 * @param workbook				HSSFWorkbook
	 * @param sourceSheetName		源sheet名称
	 * @param targetSheetName		目标sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean copySheet(HSSFWorkbook workbook, String sourceSheetName,String targetSheetName) throws EcxlsException{
		return this.excelHelp.copySheet(workbook, sourceSheetName, targetSheetName);
	}
	/**
	 * @MethodName copySheet
	 * 通过源sheet码和目标sheet码获得源sheet对象和目标sheet对象，把指定excel的源sheet上的内容复制到目标sheet上
	 * @author chenluning
	 * @param workbook				HSSFWorkbook
	 * @param sourceSheetNum		源sheet名称
	 * @param targetSheetNum		目标sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean copySheet(HSSFWorkbook workbook, int sourceSheetNum,int targetSheetNum) throws EcxlsException{
		return this.excelHelp.copySheet(workbook, sourceSheetNum, targetSheetNum);
	}
	/**
	 * @MethodName copySheet
	 * 通过源sheet码和目标sheet名称获得源sheet对象和目标sheet对象，把指定excel的源sheet上的内容复制到目标sheet上
	 * @author chenluning
	 * @param workbook				HSSFWorkbook
	 * @param sourceSheetNum		源sheet名称
	 * @param targetSheetName		目标sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean copySheet(HSSFWorkbook workbook, int sourceSheetNum,String targetSheetName) throws  EcxlsException{
		return this.excelHelp.copySheet(workbook, sourceSheetNum, targetSheetName);
	}
	
	/**
	 * @MethodName renameSheet
	 * 在默认的excel上， 给源sheet名称重命名
	 * @author chenluning
	 * @param oldSheetName			源sheet名称
	 * @param newSheetName			新的sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException 
	 */
	public boolean renameSheet(String oldSheetName, String newSheetName)
			throws EcxlsException {
		return this.excelHelp.renameSheet(this.workbook,oldSheetName, newSheetName);
	}
	/**
	 * @MethodName renameSheet
	 * 在默认的excel上， 给源sheet名称重命名
	 * @author chenluning
	 * @param sheetNum			源sheet码
	 * @param newSheetName		新的sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException 
	 */
	public boolean renameSheet(int sheetNum, String newSheetName) throws EcxlsException{
		return this.excelHelp.renameSheet(this.workbook, sheetNum, newSheetName);
	}
	/**
	 * @MethodName renameSheet
	 * 在指定的excel上，给源sheet名称重命名
	 * @author chenluning
	 * @param workbook			HSSFWorkbook
	 * @param oldSheetName		源sheet名称
	 * @param newSheetName		新的sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean renameSheet(HSSFWorkbook workbook, String oldSheetName,
			String newSheetName) throws EcxlsException {
		return this.excelHelp.renameSheet(workbook, oldSheetName, newSheetName);
	}
	/**
	 * @MethodName renameSheet
	 * 在指定的excel上，给源sheet名称重命名
	 * @author chenluning
	 * @param workbook			HSSFWorkbook
	 * @param sheetNum			源sheet码
	 * @param newSheetName		新的sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean renameSheet(HSSFWorkbook workbook, int sheetNum, String newSheetName)
			throws EcxlsException {
		return this.excelHelp.renameSheet(workbook, sheetNum, newSheetName);
	}
	
	/**
	 * @MethodName removeSheet
	 * 在默认的excel上，通过sheet名称获得sheet对象，然后删除此sheet
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 * @see lead.common.Excel#removeSheet(java.lang.String)
	 */
	public boolean removeSheet(String sheetName) throws EcxlsException {
		return this.excelHelp.removeSheet(this.workbook,sheetName);
	}
	/**
	 * @MethodName removeSheet
	 * 在默认的excel上，通过sheet码获得sheet对象，然后删除此sheet
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 * @see lead.common.Excel#removeSheet(int)
	 */
	public boolean removeSheet(int sheetNum) throws EcxlsException {
		return this.excelHelp.removeSheet(this.workbook,sheetNum);
	}
	/**
	 * @MethodName removeSheet
	 * 通过sheet名称获得sheet对象，然后指定的excel删除此sheet
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeSheet(HSSFWorkbook workbook, String sheetName)
			throws EcxlsException {
		return this.excelHelp.removeSheet(workbook,sheetName);
	}
	
	/**
	 * @MethodName removeSheet
	 * 通过sheet码获得sheet对象，然后指定excel删除此sheet
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeSheet(HSSFWorkbook workbook, int sheetNum) throws EcxlsException {
		return this.excelHelp.removeSheet(workbook,sheetNum);
	}
	
	/**
	 * @MethodName removeRow
	 * 在默认的excel上，通过sheet名称获得sheet对象，然后删除该sheet中的指定rowNun
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeRow(String sheetName, int rowNum) throws EcxlsException {
		return this.excelHelp.removeRow(this.workbook,sheetName, rowNum);
	}
	/**
	 * @MethodName removeRow
	 * 在默认的excel上，通过sheet码获得sheet对象，然后删除该sheet中的指定
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeRow(int sheetNum, int rowNum) throws EcxlsException {
		return this.excelHelp.removeRow(this.workbook,sheetNum, rowNum);
	}
	/**
	 * @MethodName removeRow
	 * 通过sheet名称获得sheet对象，然后删除指定excel的该sheet中的指定rowNun
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeRow(HSSFWorkbook workbook, String sheetName, int rowNum)
			throws EcxlsException {
		return this.excelHelp.removeRow(workbook, sheetName, rowNum);
	}
	/**
	 * @MethodName removeRow
	 * 通过sheet码获得sheet对象，然后删除指定excel的该sheet中的指定rowNun
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeRow(HSSFWorkbook workbook, int sheetNum, int rowNum)
			throws EcxlsException {
		return this.excelHelp.removeRow(workbook, sheetNum, rowNum);
	}
	
	/**
	 * @MethodName removeCell
	 * 在默认的excel上，通过sheet名称获得sheet对象，然后删除该sheet中的指定cellNum
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param cellNum		指定列下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeCell(String sheetName, int cellNum)
			throws EcxlsException {
		return this.excelHelp.removeCell(this.workbook,sheetName, cellNum);
	}
	/**
	 * @MethodName removeCell
	 * 在默认的excel上，通过sheet码获得sheet对象，然后删除该sheet中的指定rowNum
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param cellNum		指定列下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeCell(int sheetNum, int cellNum) throws EcxlsException {
		return this.excelHelp.removeCell(this.workbook,sheetNum, cellNum);
	}
	/**
	 * @MethodName removeCell
	 * 通过sheet名称获得sheet对象，然后删除指定excel的该sheet中的指定cellNun
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称	
	 * @param cellNum		指定列下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeCell(HSSFWorkbook workbook, String sheetName, int cellNum)
			throws EcxlsException {
		return this.excelHelp.removeCell(workbook, sheetName, cellNum);
	}
	/**
	 * @MethodName removeCell
	 * 通过sheet码获得sheet对象，然后删除指定excel的该sheet中的指定cellNun
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param cellNum		指定列下标
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean removeCell(HSSFWorkbook workbook, int sheetNum, int cellNum)
			throws EcxlsException {
		return this.excelHelp.removeCell(workbook, sheetNum, cellNum);
	}
	
	/**
	 * @MethodName insertCell
	 * 在默认的excel上，根据sheet名称，行号,列号设定单元格值
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(String sheetName, int rowNum, int cellNum,
			String value) throws EcxlsException {
		return this.excelHelp.insertCell(this.workbook,sheetName, rowNum, cellNum, value,null);
	}
	/**
	 * @MethodName insertCell
	 * 在默认的excel上，根据sheet码，行号,列号设定单元格值
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @return 	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(int sheetNum, int rowNum, int cellNum,
			String value) throws EcxlsException {
		return this.excelHelp.insertCell(this.workbook,sheetNum, rowNum, cellNum, value,null);
	}
	/**
	 * @MethodName insertCell
	 * 在默认的excel上，根据sheet名称，行号,列号,样式设定单元格值
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			指定样式
	 * @param style			要设置的值
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(String sheetName, int rowNum, int cellNum,
			String value, HSSFCellStyle style) throws EcxlsException {
		return this.excelHelp.insertCell(this.workbook, sheetName, rowNum, cellNum, value, style);
	}
	/**
	 * @MethodName insertCell
	 * 在默认的excel上，根据sheet码，行号,列号,样式设定单元格值
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @param style			指定样式
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(int sheetNum, int rowNum, int cellNum,
			String value, HSSFCellStyle style) throws EcxlsException {
		return this.excelHelp.insertCell(this.workbook, sheetNum, rowNum, cellNum, value, style);
	}
	/**
	 * @MethodName insertCell
	 * 根据sheet名称，行号,列号给指定excel设定单元格值
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(HSSFWorkbook workbook, String sheetName, int rowNum,
			int cellNum, String value) throws EcxlsException {
		return this.excelHelp.insertCell(workbook, sheetName, rowNum, cellNum, value, null);
	}
	/**
	 * @MethodName insertCell
	 * 根据sheet码，行号,列号给指定excel设定单元格值
	 * @author chenluning
	 * @param file			指定excel文件
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(HSSFWorkbook workbook, int sheetNum, int rowNum,
			int cellNum, String value) throws EcxlsException {
		return this.excelHelp.insertCell(workbook, sheetNum, rowNum, cellNum, value, null);
	}
	/**
	 * @MethodName insertCell
	 * 根据sheet名称，行号,列号,样式给指定excel设定单元格值
	 * @author chenluning
	 * @param path			指定excel文件
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @param style			要设定的样式
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(HSSFWorkbook workbook, String sheetName, int rowNum,
			int cellNum, String value, HSSFCellStyle style)
			throws EcxlsException {
		return this.excelHelp.insertCell(workbook, sheetName, rowNum, cellNum, value, style);
	}
	/**
	 * @MethodName insertCell
	 * 根据sheet码，行号,列号,样式给指定excel设定单元格值
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param cellNum		指定列下标
	 * @param value			要设置的值
	 * @param style			要设定的样式
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertCell(HSSFWorkbook workbook, int sheetNum, int rowNum,
			int cellNum, String value, HSSFCellStyle style)
			throws EcxlsException {
		return this.excelHelp.insertCell(workbook, sheetNum, rowNum, cellNum, value, style);
	}
	
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号设定该行所有列值，默认第一列开始
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(String sheetName, int rowNum, String[] values)
			throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetName, rowNum,0,null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号,起始列号设定该行各列值
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param startCellNum	指定起始列下标
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(String sheetName, int rowNum, int startCellNum,
			String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetName, rowNum,startCellNum,null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号设定该行所有列值,根据列名称对应设置
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRowP(String sheetName, int rowNum,
			Map<String, String> values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetName, rowNum,0,null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号,设定该行各列值(根据列名称匹配)
	 * @author chenluning
	 * @param sheetNum		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(int sheetNum, int rowNum,
			Map<String, String> values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetNum, rowNum, 0, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号，行样式设定该行所有列值,根据列名称对应设置
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param style			行样式
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(String sheetName, int rowNum, HSSFCellStyle style,
			Map<String, String> values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetName, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号,行样式设定该行各列值(根据列名称匹配)
	 * @author chenluning
	 * @param sheetNum		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param style			行样式
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(int sheetNum, int rowNum, HSSFCellStyle style,
			Map<String, String> values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetNum, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号,样式设定该行的各列值和样式，默认从第一列开始
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param style			指定样式
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(String sheetName, int rowNum, HSSFCellStyle style,
			String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetName, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet名称，行号,起始列号,样式设定单元格值和样式
	 * @author chenluning
	 * @param sheetName			指定sheet名称
	 * @param rowNum			指定行下标
	 * @param startCellNum		指定起始列下标
	 * @param style				指定样式
	 * @param values			要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(String sheetName, int rowNum, int startCellNum,
			HSSFCellStyle style, String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetName, rowNum, startCellNum, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet码，行号设定该行所有列值，默认第一列开始
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(int sheetNum, int rowNum, String[] values)
			throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetNum, rowNum, 0, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet码，行号,起始列号设定该行各列值
	 * @author chenluning
	 * @param sheetNum			指定sheet码
	 * @param rowNum			指定行下标
	 * @param startCellNum		指定起始列下标
	 * @param values			要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(int sheetNum, int rowNum, int startCellNum,
			String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetNum, rowNum, startCellNum, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet码，行号,样式设定该行的各列值和样式，默认从第一列开始
	 * @author chenluning
	 * @param sheetNum			指定sheet码
	 * @param rowNum			指定行下标
	 * @param style				指定样式
	 * @param values			要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(int sheetNum, int rowNum, HSSFCellStyle style,
			String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetNum, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在默认的excel上，根据sheet码，行号,起始列号,样式设定单元格值和样式
	 * @author chenluning
	 * @param sheetNum			指定sheet码
	 * @param rowNum			指定行下标
	 * @param startCellNum		指定起始列下标
	 * @param style				指定样式
	 * @param values			要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(int sheetNum, int rowNum, int startCellNum,
			HSSFCellStyle style, String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(this.workbook, sheetNum, rowNum, startCellNum, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在指定的excel上，根据sheet名称，行号设定该行所有列值,根据列名称对应设置
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, String sheetName, int rowNum,
			Map<String, String> values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetName, rowNum, 0, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在指定的excel上，根据sheet名称，行号,设定该行各列值(根据列名称匹配)
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, int sheetNum, int rowNum,
			Map<String, String> values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetNum, rowNum, 0, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在指定的excel上，根据sheet名称，行号,设定该行各列值(根据列名称匹配)
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, int sheetNum, int rowNum,
			String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetNum, rowNum, 0, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 在指定的excel上，根据sheet名称，行号，行样式设定该行所有列值,根据列名称对应设置 
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param style			行样式
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, String sheetName, int rowNum,
			HSSFCellStyle style, Map<String, String> values)
			throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetName, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 在指定的excel上，根据sheet名称，行号,行样式设定该行各列值(根据列名称匹配)
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param style			行样式
	 * @param values		要设置的值map对象
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, int sheetNum, int rowNum,
			HSSFCellStyle style, Map<String, String> values)
			throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetNum, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 根据sheet名称，行号给指定excel设定该行所有列值，默认第一列开始
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, String sheetName, int rowNum,
			String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetName, rowNum, 0, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 根据sheet名称，行号,起始列号给指定excel设定该行各列值
	 * @author chenluning
	 * @param workbook			HSSFWorkbook
	 * @param sheetName			指定sheet名称
	 * @param rowNum			指定行下标
	 * @param startCellNum		指定起始列下标
	 * @param values			要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, String sheetName, int rowNum,
			int startCellNum, String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetName, rowNum, startCellNum, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 根据sheet名称，行号,样式给指定excel设定该行的各列值和样式，默认从第一列开始
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowNum		指定行下标
	 * @param style			指定样式
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, String sheetName, int rowNum,
			HSSFCellStyle style, String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetName, rowNum, 0, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 根据sheet名称，行号,起始列号,样式给指定excel设定该行各列值和样式
	 * @author chenluning
	 * @param workbook			HSSFWorkbook
	 * @param sheetName			指定sheet名称
	 * @param rowNum			指定行下标
	 * @param startCellNum		指定起始列下标
	 * @param style				指定样式
	 * @param values			要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, String sheetName, int rowNum,
			int startCellNum, HSSFCellStyle style, String[] values)
			throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetName, rowNum, startCellNum, style,values);
	}
	/**
	 * @MethodName insertRow
	 * 根据sheet码，行号,起始列号给指定excel设定该行各列值
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param startCellNum	指定起始列下标
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, int sheetNum, int rowNum,
			int startCellNum, String[] values) throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetNum, rowNum, startCellNum, null,values);
	}
	/**
	 * @MethodName insertRow
	 * 根据sheet码，行号,起始列号,样式给指定excel设定该行各列值和样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowNum		指定行下标
	 * @param startCellNum	指定起始列下标
	 * @param style			指定样式
	 * @param values		要设置的值数组
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean insertRow(HSSFWorkbook workbook, int sheetNum, int rowNum,
			int startCellNum, HSSFCellStyle style, String[] values)
			throws EcxlsException {
		return this.excelHelp.insertRow(workbook, sheetNum, rowNum, startCellNum, style,values);
	}
	
	/**
	 * @MethodName getDatas
	 * 在默认的excel上，根据sheet名称、开始行、结束行、开始列、结束列读取部分数据存到list集合里面
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @return List Object
	 * @throws EcxlsException
	 * @see lead.common.Excel#getDatas(java.lang.String, int, int, int, int)
	 */
	public List<Object> getDatas(String sheetName, int startRow, int endRow,
			int startCell, int endCell) throws EcxlsException {
		return this.excelHelp.getDatas(this.workbook,sheetName, startRow, endRow, startCell, endCell);
	}
	/**
	 * @MethodName getDatas
	 * 在默认的excel上， 根据sheet码、开始行、结束行、开始列、结束列读取部分数据存到list集合里面
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @return List Object
	 * @throws EcxlsException
	 * @see lead.common.Excel#getDatas(int, int, int, int, int)
	 */
	public List<Object> getDatas(int sheetNum, int startRow, int endRow,
			int startCell, int endCell) throws EcxlsException {
		return this.excelHelp.getDatas(this.workbook, sheetNum, startRow, endRow, startCell, endCell);
	}
	/**
	 * @MethodName getDatas
	 * 根据sheet名称、开始行、结束行、开始列、结束列读取指定excel中部分数据存到list集合里面
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @return List Object
	 * @throws EcxlsException
	 */
	public List<Object> getDatas(HSSFWorkbook workbook, String sheetName, int startRow,
			int endRow, int startCell, int endCell) throws EcxlsException {
		return this.excelHelp.getDatas(workbook, sheetName, startRow, endRow, startCell, endCell);
	}
	/**
	 * @MethodName getDatas
	 * 根据sheet码、开始行、结束行、开始列、结束列读取指定excel的部分数据存到list集合里面
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @return List Object
	 * @throws EcxlsException
	 */
	public List<Object> getDatas(HSSFWorkbook workbook, int sheetNum, int startRow,
			int endRow, int startCell, int endCell) throws EcxlsException {
		return this.excelHelp.getDatas(workbook, sheetNum, startRow, endRow, startCell, endCell);
	}
	
	/**
	 * @MethodName clearSheet
	 * 在默认的excel上，根据sheet名称清除该sheet中所有内容
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean clearSheet(String sheetName) throws EcxlsException {
		return this.excelHelp.clearSheet(this.workbook, sheetName);
	}
	/**
	 * @MethodName clearSheet
	 * 在默认的excel上，根据sheet码清除该sheet中所有内容
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean clearSheet(int sheetNum) throws EcxlsException {
		return this.excelHelp.clearSheet(this.workbook, sheetNum);
	}
	/**
	 * @MethodName clearSheet
	 * 根据sheet名称清除指定excel中该sheet中所有内容
	 * @author chenluning
	 * @param file			指定excel文件
	 * @param sheetName		指定sheet名称
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean clearSheet(HSSFWorkbook workbook, String sheetName)
			throws EcxlsException {
		return this.excelHelp.clearSheet(workbook,sheetName);
	}
	/**
	 * @MethodName clearSheet
	 * 根据sheet码清除指定excel中该sheet中所有内容
	 * @author chenluning
	 * @param file		指定excel文件
	 * @param sheetNum	指定sheet码
	 * @return	true：成功， false：失败
	 * @throws EcxlsException
	 */
	public boolean clearSheet(HSSFWorkbook workbook, int sheetNum) throws EcxlsException {
		return this.excelHelp.clearSheet(workbook,sheetNum);
	}
	
	/**
	 * @MethodName countRows
	 * 在默认的excel上，根据sheet名称统计该sheet中有效行数，默认从第一行开始
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(String sheetName) throws EcxlsException {
		return this.excelHelp.countRows(this.workbook,sheetName);
	}
	/**
	 * @MethodName countRows
	 * 在默认的excel上，根据sheet名称和起始行统计该sheet中有效行数
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param startRow		起始行下标
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(String sheetName, int startRow) throws EcxlsException {
		return this.excelHelp.countRows(this.workbook,sheetName, startRow);
	}
	/**
	 * @MethodName countRows
	 * 在默认的excel上，根据sheet码统计该sheet中有效行数，默认从第一行开始
	 * @author chenluning
	 * @param sheetNum	指定sheet码
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(int sheetNum) throws EcxlsException {
		return this.excelHelp.countRows(this.workbook, sheetNum);
	}
	/**
	 * @MethodName countRows
	 * 在默认的excel上，根据sheet码和起始行统计该sheet中有效行数
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param startRow		起始行下标
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(int sheetNum, int startRow) throws EcxlsException {
		return this.excelHelp.countRows(this.workbook, sheetNum, startRow);
	}
	/**
	 * @MethodName countRows
	 * 根据sheet名称统计指定excel中该sheet中有效行数，默认从第一行开始
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(HSSFWorkbook workbook, String sheetName) throws EcxlsException {
		return this.excelHelp.countRows(workbook, sheetName);
	}
	/**
	 * @MethodName countRows
	 * 根据sheet名称统计指定excel中该sheet中有效行数，默认从第一行开始
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(HSSFWorkbook workbook, int sheetNum) throws EcxlsException {
		return this.excelHelp.countRows(workbook, sheetNum);
	}
	/**
	 * @MethodName countRows
	 * 根据sheet名称和起始行统计指定excelzhong 该sheet中有效行数
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param startRow		起始行下标
	 * @return 有效行数
	 * @throws EcxlsException
	 */
	public int countRows(HSSFWorkbook workbook, String sheetName, int startRow)
			throws EcxlsException {
		return this.excelHelp.countRows(workbook, sheetName, startRow);
	}
	/**
	 * @MethodName countRows
	 * 根据sheet码和起始行统计指定excel中该sheet中有效行数
	 * @author chenluning
	 * @param workbook	HSSFWorkbook
	 * @param sheetNum	指定sheet码
	 * @param startRow	起始行下标
	 * @return 有效行数
	 * @throws EcxlsException
	 * @see lead.common.Excel#countRows(java.lang.String, int, int)
	 */
	public int countRows(HSSFWorkbook workbook, int sheetNum, int startRow)
			throws EcxlsException {
		return this.excelHelp.countRows(workbook, sheetNum, startRow);
	}
	
	/**
	 * @MethodName setRowStyle
	 * 在默认的excel上， 根据sheet名称、行号、样式对象设置该行样式
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowIndex		指定行下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setRowStyle(String sheetName, int rowIndex, HSSFCellStyle style)
			throws EcxlsException {
		this.excelHelp.setRowStyle(this.workbook, sheetName, rowIndex, style);
	}
	/**
	 * @MethodName setRowStyle
	 * 在默认的excel上，根据sheet码、行号、样式对象设置该行样式
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param rowIndex		指定行下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setRowStyle(int sheetNum, int rowIndex, HSSFCellStyle style)
			throws EcxlsException {
		this.excelHelp.setRowStyle(this.workbook, sheetNum, rowIndex, style);
	}
	/**
	 * @MethodName setRowStyle
	 * 给指定excel根据sheet名称、行号、样式对象设置该行样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowIndex		指定行下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setRowStyle(HSSFWorkbook workbook, String sheetName, int rowIndex,
			HSSFCellStyle style) throws EcxlsException {
		this.excelHelp.setRowStyle(workbook, sheetName, rowIndex, style);
	}
	/**
	 * @MethodName setRowStyle
	 * 给指定excel根据sheet码、行号、样式对象设置该行样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowIndex		指定行下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setRowStyle(HSSFWorkbook workbook, int sheetNum, int rowIndex,
			HSSFCellStyle style) throws EcxlsException {
		this.excelHelp.setRowStyle(workbook, sheetNum, rowIndex, style);
	}
	/**
	 * @MethodName setCellStyle
	 * 在默认的excel上，根据sheet名称、行号、列号、样式对象设置该行指定列样式
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param rowIndex		指定行下标
	 * @param cellIndex		指定列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setCellStyle(String sheetName, int rowIndex, int cellIndex,
			HSSFCellStyle style) throws EcxlsException {
		this.excelHelp.setCellStyle(this.workbook, sheetName, rowIndex, cellIndex, style);
	}
	/**
	 * @MethodName setCellStyle
	 * 在默认的excel上，根据sheet码、行号、列号、样式对象设置该行指定列样式
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param rowIndex		指定行下标
	 * @param cellIndex		指定列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setCellStyle(int sheetNum, int rowIndex, int cellIndex,
			HSSFCellStyle style) throws EcxlsException {
		this.excelHelp.setCellStyle(this.workbook, sheetNum, rowIndex, cellIndex, style);
	}
	/**
	 * @MethodName setCellStyle
	 * 给指定excel根据sheet名称、行号、列号、样式对象设置该行指定列样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param rowIndex		指定行下标
	 * @param cellIndex		指定列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setCellStyle(HSSFWorkbook workbook, String sheetName, int rowIndex,
			int cellIndex, HSSFCellStyle style) throws EcxlsException {
		this.excelHelp.setCellStyle(workbook, sheetName, rowIndex, cellIndex, style);
	}
	/**
	 * @MethodName setCellStyle
	 * 给指定excel根据sheet码、行号、列号、样式对象设置该行指定列样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param rowIndex		指定行下标
	 * @param cellIndex		指定列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setCellStyle(HSSFWorkbook workbook, int sheetNum, int rowIndex,
			int cellIndex, HSSFCellStyle style) throws EcxlsException {
		this.excelHelp.setCellStyle(workbook, sheetNum, rowIndex, cellIndex, style);
	}
	
	/**
	 * @MethodName setMergeCell
	 * 在默认的excel上，根据sheet名称、开始行、结束行、开始列、结束列合并单元格
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @throws EcxlsException
	 */
	public void setMergeCell(String sheetName, int startRow, int endRow,
			int startCell, int endCell) throws EcxlsException {
		this.excelHelp.setMergeCell(this.workbook, sheetName, startRow, endRow, startCell, endCell, null);
	}
	/**
	 * @MethodName setMergeCell
	 * 在默认的excel上，根据sheet名称、起始行号、结束行号、起始列号、结束列号、样式对象合并单元格并设置该列样式
	 * @author chenluning
	 * @param sheetName		指定sheet名称
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 * @see lead.common.Excel#setMergeCell(java.lang.String, int, int, int, int, org.apache.poi.hssf.usermodel.HSSFCellStyle)
	 */
	public void setMergeCell(String sheetName, int startRow, int endRow,
			int startCell, int endCell, HSSFCellStyle style)
			throws EcxlsException {
		this.excelHelp.setMergeCell(this.workbook, sheetName, startRow, endRow, startCell, endCell, style);
	}
	/**
	 * @MethodName setMergeCell
	 * 在默认的excel上， 根据sheet码、开始行、结束行、开始列、结束列合并单元格
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @throws EcxlsException
	 * @see lead.common.Excel#setMergeCell(int, int, int, int, int)
	 */
	public void setMergeCell(int sheetNum, int startRow, int endRow,
			int startCell, int endCell) throws EcxlsException {
		this.excelHelp.setMergeCell(this.workbook, sheetNum, startRow, endRow, startCell, endCell, null);
	}
	/**
	 * @MethodName setMergeCell
	 * 在默认的excel上，根据sheet码、起始行号、结束行号、起始列号、结束列号、样式对象合并单元格并设置该列样式
	 * @author chenluning
	 * @param sheetNum		指定sheet码
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setMergeCell(int sheetNum, int startRow, int endRow,
			int startCell, int endCell, HSSFCellStyle style)
			throws EcxlsException {
		this.excelHelp.setMergeCell(this.workbook, sheetNum, startRow, endRow, startCell, endCell, style);
	}
	/**
	 * @MethodName setMergeCell
	 * 给指定excel根据sheet名称、开始行、结束行、开始列、结束列合并单元格
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @throws EcxlsException
	 */
	public void setMergeCell(HSSFWorkbook workbook, String sheetName, int startRow,
			int endRow, int startCell, int endCell) throws EcxlsException {
		this.excelHelp.setMergeCell(workbook, sheetName, startRow, endRow, startCell, endCell, null);
	}
	/**
	 * @MethodName setMergeCell
	 * 给指定excel根据sheet名称、起始行号、结束行号、起始列号、结束列号、样式对象合并单元格并设置该列样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetName		指定sheet名称
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setMergeCell(HSSFWorkbook workbook, String sheetName, int startRow,
			int endRow, int startCell, int endCell, HSSFCellStyle style)
			throws EcxlsException {
		this.excelHelp.setMergeCell(workbook, sheetName, startRow, endRow, startCell, endCell, style);
	}
	/**
	 * @MethodName setMergeCell
	 * 给指定excel根据sheet码、开始行、结束行、开始列、结束列合并单元格
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @throws EcxlsException
	 */
	public void setMergeCell(HSSFWorkbook workbook, int sheetNum, int startRow,
			int endRow, int startCell, int endCell) throws EcxlsException {
		this.excelHelp.setMergeCell(workbook, sheetNum, startRow, endRow, startCell, endCell, null);
	}
	/**
	 * @MethodName setMergeCell
	 * 给指定excel根据sheet码、起始行号、结束行号、起始列号、结束列号、样式对象合并单元格并设置该列样式
	 * @author chenluning
	 * @param workbook		HSSFWorkbook
	 * @param sheetNum		指定sheet码
	 * @param startRow		指定起始行下标
	 * @param endRow		指定结束行下标
	 * @param startCell		指定起始列下标
	 * @param endCell		指定结束列下标
	 * @param style			指定列样式
	 * @throws EcxlsException
	 */
	public void setMergeCell(HSSFWorkbook workbook, int sheetNum, int startRow,
			int endRow, int startCell, int endCell, HSSFCellStyle style)
			throws EcxlsException {
		this.excelHelp.setMergeCell(workbook, sheetNum, startRow, endRow, startCell, endCell, style);
	}
	/**
	 * 
	 * @MethodName: createSheet<p>
	 * 创建一个sheet<p>
	 * @author chenluning		
	 * @param sheetname		sheet名称
	 * @throws EcxlsException 
	 */
	public Boolean createSheet(String sheetname) throws EcxlsException{
		return this.createSheet(this.workbook, sheetname);
	}
	/**
	 * 
	 * @MethodName: createSheet<p>
	 * 创建一个sheet<p>
	 * @author chenluning		
	 * @param workbook		工作薄对象
	 * @param sheetname		sheet名称
	 * @throws EcxlsException 
	 */
	public Boolean createSheet(HSSFWorkbook workbook,String sheetname) throws EcxlsException{
		try {
			workbook.createSheet(sheetname);
			return true;
		} catch (Exception e) {
			throw new EcxlsException(e);
		}
	}

	/**
	 * @return the defaultSheetName
	 */
	public String getDefaultSheetName() {
		return defaultSheetName;
	}

	/**
	 * @param defaultSheetName the defaultSheetName to set
	 */
	public void setDefaultSheetName(String defaultSheetName) {
		this.defaultSheetName = defaultSheetName;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
