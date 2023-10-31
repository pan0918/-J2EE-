package first;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@WebServlet(name = "FinderServlet",value = "/Find")
public class FinderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// contact table
	private List<Map<String, Object>> contacts = new ArrayList<Map<String, Object>>();

	public void init() throws ServletException {

		try {
			String files = getInitParameter("contacts");

			files = files.trim();
			files = files.replace('，', ',');
			String[] file_name_array = files.split(",");

			for (int i = 0; i < file_name_array.length; i++) {
				String file_name = file_name_array[i];
				file_name = file_name.trim();
				if (file_name.length() == 0) {
					continue;
				}

				File file = new File(getServletContext().getRealPath("/WEB-INF/contacts/" + file_name));

				FileInputStream fis = new FileInputStream(file);

				Workbook book = null;
				try {
					book = new XSSFWorkbook(fis);
				} catch (Exception ex) {
					book = new HSSFWorkbook(fis);
				}

				Sheet sheetAt = book.getSheetAt(0);

				for (Row row : sheetAt) {
					if (row.getRowNum() == 0) {
						continue;
					}

					if (row == null) {
						break;
					}

					Cell cell = row.getCell(0);

					if (cell == null) {
						break;
					}

					double no = row.getCell(0).getNumericCellValue();
					String id = row.getCell(1).getStringCellValue();
					String name = row.getCell(2).getStringCellValue();
					String gender;
					if(name.endsWith("*")){
						name = name.substring(0, name.length()-1);
						gender = "女";
					}else{
						gender = "男";
					}
					String strClass = "";
					String mobile = "";
					String email = "";

					cell = row.getCell(3);
					if (cell != null) {
						strClass = cell.getStringCellValue();
					}

					cell = row.getCell(4);
					if (cell != null) {
						cell.setCellType(CellType.STRING);
						mobile = cell.getStringCellValue();
					}

					cell = row.getCell(5);
					if (cell != null) {
						cell.setCellType(CellType.STRING);
						email = cell.getStringCellValue();
					}

					Map<String, Object> record = new HashMap<String, Object>();
					record.put("id", id);
					record.put("name", name);
					record.put("gender", gender);
					record.put("class", strClass);
					record.put("mobile", mobile);
					record.put("email", email);

					contacts.add(record);

				}

				book.close();
				fis.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String query = request.getParameter("query");
		//Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		/*byte[] bytes = query.getBytes(StandardCharsets.ISO_8859_1);
		query = new String(bytes, StandardCharsets.UTF_8);*/
		System.out.println(query);


		// 在联系人列表中搜索匹配的联系人
		List<Map<String, Object>> results = new ArrayList<>();
		for (Map<String, Object> contact : contacts) {
			if (contact.get("id").equals(query) || contact.get("name").equals(query) ||
					contact.get("class").equals(query) || contact.get("mobile").equals(query) || contact.get("email").equals(query)) {
				results.add(contact);
			}
		}

		// 将搜索结果发送回客户端
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>查找结果</h1>");
		out.println("<table border='1'>");
		out.println("<tr><th>ID</th><th>Name</th><th>Gender</th><th>Class</th><th>Mobile</th><th>Email</th></tr>");
		for (Map<String, Object> result : results) {
			out.println("<tr>");
			out.println("<td>" + result.get("id") + "</td>");
			out.println("<td>" + result.get("name") + "</td>");
			out.println("<td>" + result.get("gender") + "</td>");
			out.println("<td>" + result.get("class") + "</td>");
			out.println("<td>" + result.get("mobile") + "</td>");
			out.println("<td>" + result.get("email") + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body></html>");
	}
}
