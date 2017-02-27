package com.travelhelper.service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.tool.schema.internal.exec.GenerationTargetToStdout;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.travelhelper.dao.TravelServiceDao;
import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

@Service
public class TravelServiceImpl implements TravelService{
	
	@Autowired
	private TravelServiceDao travelDao;
	
	public final static String AUTH_KEY_FCM = "AIzaSyAAZwbKFn9it2GY9bZWJwwvzYzdVKZiEno";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	@Override
	@Transactional
	public int saveTravelModeSelected(TravelModeSelected travelData) {
		int travelDriveId = travelDao.fetchDriveIdFromName(travelData.getUserDrive());
		int modeId = travelDao.fetchModeIdFromName(travelData.getModeName());
		travelData.setDrive(travelDriveId);
		travelData.setTravelMode(modeId);
		return travelDao.saveUserTravelSelection(travelData);
	}

	@Override
	public void pushFCMNotification(String clientDeviceID) {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;
        try{
	        URL url = new URL(FMCurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "key=" + authKey);
	        conn.setRequestProperty("Content-Type", "application/json");

	        JSONObject data = new JSONObject();
	        data.put("to", clientDeviceID.trim());
	        JSONObject info = new JSONObject();
	        info.put("title", "FCM Notificatoin Title"); // Notification title
	        info.put("body", "Hello First Test notification"); // Notification body
	        data.put("data", info);

	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data.toString());
	        wr.flush();
	        wr.close();

	        int responseCode = conn.getResponseCode();
	        System.out.println("Response Code : " + responseCode);

	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
       
	}

	@Override
	public int saveFutureScheduledRequest(FutureTravel fTravel) {
		fTravel.setRequestSavetime(new Date());
		System.out.println("Travel Name : "+fTravel.getTravelDriveSelected());
		int driveID = travelDao.fetchDriveIdFromName(fTravel.getTravelDriveSelected());
		System.out.println("Drive ID in saveFutureScheduledRequest :"+driveID);
		fTravel.setTravelDriveId(driveID);
		return travelDao.saveFutureScheduledRequest(fTravel);
	}

	@Override
	public String getUberAuthentiationToken(String code) {
		return travelDao.retrieveUberAuthenticationToken(code);
	}

	@Override
	public List<TravelModeSelected> getPastTravelHistory(int userId) {
		return travelDao.getPastTravelHistory(userId);
	}
	
	@Override
	public List<TravelModeSelected> getDateRangePastTravelHistory(int userId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return travelDao.getDateRangePastTravelHistory(userId, startDate, endDate);
	}

	@Override
	public List<FutureTravel> getPastFutureScheduleHistory(int userId) {
		return travelDao.getPastFutureScheduleHistory(userId);
	}
	
	
	@Override
	public List<FutureTravel> getDateRangePastFutureScheduleHistory(int userId,Date startDate, Date endDate) {
		return travelDao.getDateRangePastFutureScheduleHistory(userId,startDate,endDate);
	}
	

	@Override
	public Map<String, Long> fetchScheduledTravelSummaryBasedonDrive(int userId) {
		return travelDao.fetchScheduledTravelSummaryBasedonDrive(userId);
	}
	
	@Override
	public Map<String, Long> fetchDateRangeScheduledTravelSummaryBasedonDrive(int userId, Date startDate,
			Date endDate) {
		return travelDao.fetchDateRangeScheduledTravelSummaryBasedonDrive(userId,startDate,endDate);
	}


	@Override
	public Map<String, Long> fetchTravelHistorySummaryBasedonDrive(int userId) {
		return travelDao.fetchTravelHistorySummaryBasedonDrive(userId);
	}

	@Override
	public void fetchTravelHistoryDownload(HttpServletRequest req, HttpServletResponse resp, int userId){
		
		System.out.println("Downloading Travel History");
		String fileName = "TravelHistory_"+userId+".pdf";
		String path = "D:\\EclipseWorkspace\\TravelHelperProj\\downloadfiles";
		path = path + File.separator + fileName;
		File file = new File(path);
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + "\"");

		FileInputStream fileInputStream;
		
		try {
			fileInputStream = new FileInputStream(file);
			OutputStream osstream = resp.getOutputStream();
			int i;
			while ((i = fileInputStream.read()) != -1) {
				resp.getOutputStream().write(i);
			}
			osstream.flush();
			osstream.close();
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generateTravelHistroyPdf(List<TravelModeSelected> list,int userId,String name) {
		System.out.println("********************Generating pdf************************");
		Document doc = new Document();
		try{
			String path = "D:\\EclipseWorkspace\\TravelHelperProj\\downloadfiles";
			path = path + File.separator + "TravelHistory_"+userId+".pdf";
			File file = new File(path);
			FileOutputStream os = new FileOutputStream(file);
			PdfWriter writer = PdfWriter.getInstance(doc, os);
			doc.open();
			doc.add(new Paragraph("Travel History Report : "+name));
			doc.add(new Paragraph("Report generated on : "+new Date()));
			doc.addAuthor("TravelHelper");
			
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);
			
			float [] columnwidth = {0.55f, 1f, 1f, 1f, 1f, 1f};
			table.setWidths(columnwidth);
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("ID"));
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell11);
			
			PdfPCell cell22 = new PdfPCell(new Paragraph("Request Time"));
			cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell22.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell22);
			
			PdfPCell cell33 = new PdfPCell(new Paragraph("Drive"));
			cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell33.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell33);
			
			PdfPCell cell55 = new PdfPCell(new Paragraph("Distance (miles)"));
			cell55.setBackgroundColor(new BaseColor(Color.lightGray));
			cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell55);
			
			PdfPCell cell66 = new PdfPCell(new Paragraph("Duration (seconds)"));
			cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell66.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell66);
			
			PdfPCell cell77 = new PdfPCell(new Paragraph("Cost (dollars)"));
			cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell77.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell77);
			
			
			for(TravelModeSelected obj :  list){
				PdfPCell cell1 = new PdfPCell(new Paragraph(obj.getTravelRequestId()+""));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				
				PdfPCell cell2 = new PdfPCell(new Paragraph(obj.getRequestTimeStamp()+""));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell2);
				
				PdfPCell cell3 = new PdfPCell(new Paragraph(obj.getUserDrive()+""));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);
				
				
				PdfPCell cell5 = new PdfPCell(new Paragraph(obj.getDistance()+""));
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell5);
				
				PdfPCell cell6 = new PdfPCell(new Paragraph(obj.getDuration()+""));
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell6);
				
				PdfPCell cell7 = new PdfPCell(new Paragraph(obj.getCost()+""));
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell7);
				
				/*PdfPCell cell8 = new PdfPCell(new Paragraph(obj.getSourceLatitude()+","+obj.getSourceLongitude()));
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell8);
				
				PdfPCell cell9 = new PdfPCell(new Paragraph(obj.getDestLatitude()+","+obj.getDestLongitude()));
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell9);*/
			}
			
			doc.add(table);
			doc.close();
			writer.close();
		}catch(DocumentException e){
			e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	public void generateScheduleHistroyPdf(List<FutureTravel> list, int userId, String name) {
		System.out.println("********************Generating schedule history pdf************************");
		Document doc = new Document();
		try{
			String path = "D:\\EclipseWorkspace\\TravelHelperProj\\downloadfiles";
			path = path + File.separator + "ScheduleHistory_"+userId+".pdf";
			File file = new File(path);
			FileOutputStream os = new FileOutputStream(file);
			PdfWriter writer = PdfWriter.getInstance(doc, os);
			doc.open();
			doc.add(new Paragraph("Schedule History Report : "+name));
			doc.add(new Paragraph("Report generated on : "+new Date()));
			doc.addAuthor("TravelHelper");
			
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);
			
			float [] columnwidth = {0.55f, 1f, 1f, 1f, 1f, 1f};
			table.setWidths(columnwidth);
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("ID"));
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell11);
			
			PdfPCell cell22 = new PdfPCell(new Paragraph("Request Time"));
			cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell22.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell22);
			
			PdfPCell cell33 = new PdfPCell(new Paragraph("Drive"));
			cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell33.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell33);
			
			PdfPCell cell55 = new PdfPCell(new Paragraph("Destination Reach Time"));
			cell55.setBackgroundColor(new BaseColor(Color.lightGray));
			cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell55);
			
			PdfPCell cell66 = new PdfPCell(new Paragraph("Notification Time"));
			cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell66.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell66);
			
			PdfPCell cell77 = new PdfPCell(new Paragraph("Travel Time (seconds)"));
			cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell77.setBackgroundColor(new BaseColor(Color.lightGray));
			table.addCell(cell77);
			
			
			for(FutureTravel obj :  list){
				PdfPCell cell1 = new PdfPCell(new Paragraph(obj.getRecordId()+""));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				
				PdfPCell cell2 = new PdfPCell(new Paragraph(obj.getRequestSavetime()+""));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell2);
				
				PdfPCell cell3 = new PdfPCell(new Paragraph(obj.getTravelDriveSelected()+""));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);
				
				
				PdfPCell cell5 = new PdfPCell(new Paragraph(obj.getDestinationReachTime()+""));
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell5);
				
				PdfPCell cell6 = new PdfPCell(new Paragraph(obj.getNotificationTime()+""));
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell6);
				
				PdfPCell cell7 = new PdfPCell(new Paragraph(obj.getTraveltimeExpected()+""));
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell7);
				
				/*PdfPCell cell8 = new PdfPCell(new Paragraph(obj.getSourceLatitude()+","+obj.getSourceLongitude()));
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell8);
				
				PdfPCell cell9 = new PdfPCell(new Paragraph(obj.getDestLatitude()+","+obj.getDestLongitude()));
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell9);*/
			}
			
			doc.add(table);
			
			
			doc.close();
			writer.close();
		}catch(DocumentException e){
			e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void fetchScheduleHistoryDownload(HttpServletRequest request, HttpServletResponse resp, int userId) {

		
		System.out.println("Downloading Travel History");
		String fileName = "ScheduleHistory_"+userId+".pdf";
		String path = "D:\\EclipseWorkspace\\TravelHelperProj\\downloadfiles";
		path = path + File.separator + fileName;
		File file = new File(path);
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + "\"");

		FileInputStream fileInputStream;
		
		try {
			fileInputStream = new FileInputStream(file);
			OutputStream osstream = resp.getOutputStream();
			int i;
			while ((i = fileInputStream.read()) != -1) {
				resp.getOutputStream().write(i);
			}
			osstream.flush();
			osstream.close();
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public Map<String, Long> fetchDateRangeTravelHistorySummaryBasedonDrive(int userId, Date startDate, Date endDate) {
		return travelDao.fetchDateRangeTravelHistorySummaryBasedonDrive(userId, startDate, endDate);
	}

	
	
}
