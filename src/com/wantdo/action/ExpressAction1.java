package com.wantdo.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wantdo.utils.ExCompany;
import com.wantdo.utils.ExcelUtil;
import com.wantdo.utils.JSONUtils;

public class ExpressAction1 extends ActionSupport {
	private File sendupload;
	private String senduploadFileName;
	private String senduploadContentType;
	private List<String[]> excSucList;
	private List<String[]> excErrList;
	private List<String[]> disSucList;
	private List<String[]> disErrList;
	private ServletContext context;
	private String excSucName;
	private String excErrName;
	private int totalNum;
	private int dealNum;
	ExcelUtil excelUtil;
	private int receivedNum = 0;
	private int arrvingNum = 0;
	private int errNum = 0;
	private static String[] excHeader = { "订货日期", "自发/代发", "网上订单", "日期",
			"商店名称", "用户昵称", "收货人", "手机号", "电话", "顾客地址", "邮编", "平台订单", "支付方式",
			"快递公司", "运费", "订单总金额", "其他折扣", "客服名称", "仓库", "行号", "商品名称", "商品条形码",
			"货号", "事物特性值", "数量", "单价", "运费", "合计", "商家备注", "客户留言", "快递公司",
			"物流单号" };
	private static String[] displayHeader = { "商店名称", "收货人", "手机号", "平台订单",
			"货号", "事务特征值", "数量", "快递公司", "物流单号", "快递状态" };
	private static String[] excHeader2 = { "订货日期", "采购订单号", "供应商名称", "商品名称",
			"条形码", "货号", "事物特性", "数量", "价格", "运费", "总价", "备注", "快递公司", "快递单号" };
	private static String[] displayHeader2 = { "采购订单号", "供应商名称", "商品名称", "货号",
			"事物特性", "数量", "快递公司", "快递单号", "快递状态" };
	private static int[] displayCol = { 4, 6, 7, 11, 22, 23, 24, 30, 31 };
	private static int[] displayCol2 = { 1, 2, 3, 5, 6, 7, 12, 13 };

	public ExpressAction1() {
		super();
		// TODO Auto-generated constructor stub
		totalNum = 0;
		dealNum = 0;
		context = ServletActionContext.getServletContext();
		excSucList = new ArrayList<String[]>();
		excErrList = new ArrayList<String[]>();
		disSucList = new ArrayList<String[]>();
		disErrList = new ArrayList<String[]>();
	}

	/**
	 * @Title: execute
	 * @Description: TODO
	 * @param @return 设定文件
	 * @return short 返回类型
	 * @throws
	 */
	public String execute() {
		// TODO Auto-generated method stub
		List<String[]> list = new ArrayList<String[]>();
		HttpClient httpClient = null;
		try {
			if (sendupload != null) {
				excelUtil = new ExcelUtil(sendupload);
				if (excelUtil.getColumnNum(0) == 32) {
					list = ExCompany
							.getEnByCn(excelUtil.getRowAndColData(0, new int[] {
									30, 31 }, 1, excelUtil.getRowNum(0) - 1));
					totalNum = list.size();
					ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
					cm.setMaxTotal(50);
					httpClient = new DefaultHttpClient(cm);
					String[] urisToGet = new String[list.size()];
					for (int i = 0; i < list.size(); ++i) {
						String com = list.get(i)[0];
						if (com == null) {
							com = "null";
						} else {
							com = com.trim();
							com = com.replaceAll(" ", "");
						}
						String nu = list.get(i)[1];
						if (nu == null) {
							nu = "null";
						} else {
							nu = nu.trim();
							nu = nu.replaceAll(" ", "");
						}
						urisToGet[i] = "http://api.ickd.cn/?id=103340&secret=2cd1a18a0d8687bd7e791298faca6f30"
								+ "&com="
								+ com
								+ "&nu="
								+ nu
								+ "&type=json&encode=utf8&ord=desc";
						System.out.println(urisToGet[i]);
					}
					GetThread[] threads = new GetThread[urisToGet.length];
					for (int i = 0; i < threads.length; ++i) {
						HttpGet httpGet = new HttpGet(urisToGet[i]);
						threads[i] = new GetThread(httpClient, httpGet, i + 1,
								excelUtil.getRowData(0, i + 1));
					}
					for (int i = 0; i < threads.length; ++i) {
						threads[i].start();
					}
					for (int i = 0; i < threads.length; ++i) {
						threads[i].join();
					}
				} else if (excelUtil.getColumnNum(0) == 14) {
					list = ExCompany
							.getEnByCn(excelUtil.getRowAndColData(0, new int[] {
									12, 13 }, 1, excelUtil.getRowNum(0) - 1));
					totalNum = list.size();
					ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
					cm.setMaxTotal(50);
					httpClient = new DefaultHttpClient(cm);
					String[] urisToGet = new String[list.size()];
					for (int i = 0; i < list.size(); ++i) {
						String com = list.get(i)[0];
						if (com == null) {
							com = "null";
						} else {
							com = com.trim();
							com = com.replaceAll(" ", "");
						}
						String nu = list.get(i)[1];
						if (nu == null) {
							nu = "null";
						} else {
							nu = nu.trim();
							nu = nu.replaceAll(" ", "");
						}
						urisToGet[i] = "http://api.ickd.cn/?id=103340&secret=2cd1a18a0d8687bd7e791298faca6f30"
								+ "&com="
								+ com
								+ "&nu="
								+ nu
								+ "&type=json&encode=utf8&ord=desc";
						System.out.println(urisToGet[i]);
					}
					GetThread[] threads = new GetThread[urisToGet.length];
					for (int i = 0; i < threads.length; ++i) {
						HttpGet httpGet = new HttpGet(urisToGet[i]);
						threads[i] = new GetThread(httpClient, httpGet, i + 1,
								excelUtil.getRowData(0, i + 1));
					}
					for (int i = 0; i < threads.length; ++i) {
						threads[i].start();
					}
					for (int i = 0; i < threads.length; ++i) {
						threads[i].join();
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//httpClient.getConnectionManager().shutdown();
		}

		if (excelUtil.getColumnNum(0) == 32) {
			System.out.println(excSucList);
			System.out.println(excErrList);
			if (!excSucList.isEmpty()) {
				excSucName = new SimpleDateFormat("MM_dd_HH_mm")
						.format(new Date()) + "_caigou_suc.xls";
			}
			if (!excErrList.isEmpty()) {
				excErrName = new SimpleDateFormat("MM_dd_HH_mm")
						.format(new Date()) + "_caigou_err.xls";
			}
			new WriteExcelThread(excSucList, "download", excSucName).start();
			new WriteExcelThread(excErrList, "error", excErrName).start();
			return SUCCESS;
		} else if (excelUtil.getColumnNum(0) == 14) {
			System.out.println(excSucList);
			System.out.println(excErrList);
			if (!excSucList.isEmpty()) {
				excSucName = new SimpleDateFormat("MM_dd_HH_mm")
						.format(new Date()) + "_caigou_suc.xls";
			}
			if (!excErrList.isEmpty()) {
				excErrName = new SimpleDateFormat("MM_dd_HH_mm")
						.format(new Date()) + "_caigou_err.xls";
			}
			new WriteExcelThread(excSucList, "download", excSucName).start();
			new WriteExcelThread(excErrList, "error", excErrName).start();
			return "success2";
		} else
			return "error";
	}

	public synchronized void addDataToList(List<String[]> list, String[] object) {
		list.add(object);
	}

	public File getSendupload() {
		return sendupload;
	}

	public void setSendupload(File sendupload) {
		this.sendupload = sendupload;
	}

	public String getSenduploadFileName() {
		return senduploadFileName;
	}

	public void setSenduploadFileName(String senduploadFileName) {
		this.senduploadFileName = senduploadFileName;
	}

	public String getSenduploadContentType() {
		return senduploadContentType;
	}

	public void setSendUploadContentType(String senduploadContentType) {
		this.senduploadContentType = senduploadContentType;
	}

	public String[] getDisplayHeader() {
		return displayHeader;
	}

	public void setDisplayHeader(String[] displayHeader) {
		this.displayHeader = displayHeader;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ExcelUtil getExcelUtil() {
		return excelUtil;
	}

	public void setExcelUtil(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}

	public static String[] getExcheader() {
		return excHeader;
	}

	public List<String[]> getExcSucList() {
		return excSucList;
	}

	public void setExcSucList(List<String[]> excSucList) {
		this.excSucList = excSucList;
	}

	public List<String[]> getExcErrList() {
		return excErrList;
	}

	public void setExcErrList(List<String[]> excErrList) {
		this.excErrList = excErrList;
	}

	public List<String[]> getDisSucList() {
		return disSucList;
	}

	public void setDisSucList(List<String[]> disSucList) {
		this.disSucList = disSucList;
	}

	public List<String[]> getDisErrList() {
		return disErrList;
	}

	public void setDisErrList(List<String[]> disErrList) {
		this.disErrList = disErrList;
	}

	public static String[] getExcHeader() {
		return excHeader;
	}

	public static void setExcHeader(String[] excHeader) {
		ExpressAction1.excHeader = excHeader;
	}

	public String getExcSucName() {
		return excSucName;
	}

	public void setExcSucName(String excSucName) {
		this.excSucName = excSucName;
	}

	public String getExcErrName() {
		return excErrName;
	}

	public void setExcErrName(String excErrName) {
		this.excErrName = excErrName;
	}

	public static int[] getDisplayCol() {
		return displayCol;
	}

	public static void setDisplayCol(int[] displayCol) {
		ExpressAction1.displayCol = displayCol;
	}

	public static String[] getExcHeader2() {
		return excHeader2;
	}

	public static void setExcHeader2(String[] excHeader2) {
		ExpressAction1.excHeader2 = excHeader2;
	}

	public static String[] getDisplayHeader2() {
		return displayHeader2;
	}

	public static void setDisplayHeader2(String[] displayHeader2) {
		ExpressAction1.displayHeader2 = displayHeader2;
	}

	public static int[] getDisplayCol2() {
		return displayCol2;
	}

	public static void setDisplayCol2(int[] displayCol2) {
		ExpressAction1.displayCol2 = displayCol2;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getDealNum() {
		return dealNum;
	}

	public void setDealNum(int dealNum) {
		this.dealNum = dealNum;
	}

	public int getReceivedNum() {
		return receivedNum;
	}

	public void setReceivedNum(int receivedNum) {
		this.receivedNum = receivedNum;
	}

	public int getArrvingNum() {
		return arrvingNum;
	}

	public void setArrvingNum(int arrvingNum) {
		this.arrvingNum = arrvingNum;
	}

	public int getErrNum() {
		return errNum;
	}

	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}

	private final class GetThread extends Thread {

		private HttpClient httpClient;
		private HttpGet httpGet;
		private int id;
		private HttpContext context;
		private String[] row;

		public GetThread(HttpClient httpClient, HttpGet httpGet, int id,
				String[] row) {
			// TODO Auto-generated constructor stub
			this.httpClient = httpClient;
			this.httpGet = httpGet;
			this.id = id;
			this.row = row;
			this.context = new BasicHttpContext();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(id + " -about to get something from "
					+ httpGet.getURI());
			try {
				HttpResponse response = httpClient.execute(httpGet, context);
				System.out.println(id + " -get executed");
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					byte[] bytes = EntityUtils.toByteArray(entity);
					System.out.println(id + " - " + bytes.length
							+ " bytes read");
					String result = new String(bytes, "UTF-8");
					System.out.println(result);
					String[] objects = new String[row.length + 1];
					for (int i = 0; i < row.length; ++i) {
						objects[i] = row[i];
					}
					if (excelUtil.getColumnNum(0) == 32) {
						String[] displays = new String[displayCol.length + 1];
						for (int i = 0; i < displayCol.length; ++i) {
							int col = displayCol[i];
							displays[i] = row[col];
						}
						if (JSONUtils.getCol(result, "errCode").equals("0")) {
							String state = JSONUtils.getCurrentState(result);
							objects[row.length] = state;
							if (state.contains("签收")) {
								++receivedNum;
							} else if (state.contains("正在派件")
									|| state.contains("杭州")) {
								++arrvingNum;
							}
							displays[displayCol.length] = state;
							addDataToList(excSucList, objects);
							addDataToList(disSucList, displays);
						} else {
							String msg = JSONUtils.getCol(result, "message");
							objects[row.length] = msg;
							++errNum;
							displays[displayCol.length] = msg;
							addDataToList(excErrList, objects);
							addDataToList(disErrList, displays);
						}
					} else if (excelUtil.getColumnNum(0) == 14) {
						String[] displays = new String[displayCol2.length + 1];
						for (int i = 0; i < displayCol2.length; ++i) {
							int col = displayCol2[i];
							displays[i] = row[col];
						}
						if (JSONUtils.getCol(result, "errCode").equals("0")) {
							String state = JSONUtils.getCurrentState(result);
							objects[row.length] = state;
							displays[displayCol2.length] = state;
							addDataToList(excSucList, objects);
							addDataToList(disSucList, displays);
						} else {
							String msg = JSONUtils.getCol(result, "message");
							objects[row.length] = msg;
							displays[displayCol2.length] = msg;
							addDataToList(excErrList, objects);
							addDataToList(disErrList, displays);
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				httpGet.abort();
				System.out.println(id + " -error " + e);
			} finally {
				++dealNum;
			}
		}

	}

	private final class WriteExcelThread extends Thread {

		private List<String[]> list;
		private String dir;
		private String fileName;

		public WriteExcelThread(List<String[]> list, String dir, String fileName) {
			// TODO Auto-generated constructor stub
			this.list = list;
			this.dir = dir;
			this.fileName = fileName;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			String downloadDir = context.getRealPath(dir);
			if (!(new File(downloadDir).isDirectory())) {
				new File(downloadDir).mkdirs();
				downloadDir = context.getRealPath(dir);
			}
			File excelFile = new File(downloadDir + File.separator + fileName);
			try {
				if (!excelFile.exists()) {
					excelFile.createNewFile();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (excelUtil.getColumnNum(0) == 32) {
				excelUtil.writeExpExcel(list, excHeader, excelFile);
			} else if (excelUtil.getColumnNum(0) == 14) {
				excelUtil.writeExpExcel(list, excHeader2, excelFile);
			}

		}

	}
}
