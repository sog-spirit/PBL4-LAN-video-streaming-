package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.READ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class VideoProcess
 */
@WebServlet("/VideoProcess")
public class VideoProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Pattern RANGE_PATTERN = Pattern.compile("bytes=(?<start>\\d*)-(?<end>\\d*)");
	private static final int BUFFER_LENGTH = 1024 * 16;
	private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String id = request.getParameter("id");
//		
////		request.getRequestDispatcher("Video.jsp").forward(request, response);
//		String fileName = "video/" + id + ".mp4";
//		ServletContext context = getServletContext();
//		InputStream input = context.getResourceAsStream(fileName);
////		BufferedInputStream bufferedInput = new BufferedInputStream(input);
////		PrintWriter pw = response.getWriter();
//		response.setContentType("video/mp4");
//		OutputStream output = response.getOutputStream();
////		BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
//		byte[] buffer = new byte[4096];
//		int read = 0;
//		while((read = input.read(buffer)) != -1) {
//			output.write(buffer, 0, read);
//		}
//		input.close();
//		output.flush();
//		output.close();
		
		String id = request.getParameter("id");
		String filePath = "D:/Personal Files/web system/xemphim/src/main/webapp/video/";
		String videoName = id + ".mp4";
		Path video = Paths.get(filePath, videoName);
		
		int length = (int) Files.size(video);
		int start = 0;
		int end = length - 1;
		
		String range = request.getHeader("Range");
		Matcher matcher = RANGE_PATTERN.matcher(range);
		
		if(matcher.matches()) {
			String startGroup = matcher.group("start");
			start = startGroup.isEmpty() ? start : Integer.valueOf(startGroup);
			start = start < 0 ? 0 : start;
			
			String endGroup = matcher.group("end");
			end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
			end = end > length - 1 ? length -1 : end;
			
		}
		
		int contentLength = end - start + 1;
		
		response.reset();
		response.setBufferSize(BUFFER_LENGTH);
		response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", videoName));
		response.setHeader("Accept-Ranges", "bytes");
		response.setDateHeader("Last-Modified", Files.getLastModifiedTime(video).toMillis());
		response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
		response.setContentType(Files.probeContentType(video));
		response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
		response.setHeader("Content-Length", String.format("%s", contentLength));
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
		
		int bytesRead;
		int bytesLeft = contentLength;
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
		
		try(SeekableByteChannel input = Files.newByteChannel(video, READ);
				OutputStream output = response.getOutputStream()) {
			
			input.position(start);
			
			while((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
				buffer.clear();
				output.write(buffer.array(), 0, bytesLeft < bytesRead ? bytesLeft : bytesRead);
				bytesLeft -= bytesRead;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
