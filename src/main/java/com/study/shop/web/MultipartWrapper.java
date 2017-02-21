package com.study.shop.web;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 傲然 on 2017/2/21.
 */
public class MultipartWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params;
    public static final String UPLOADPATH = "D:\\java\\shop\\src\\main\\webapp\\upload";

    public MultipartWrapper(HttpServletRequest request) {
        super(request);
        params = new HashMap<String, String[]>();
        setParams(request);
    }

    private void setParams(HttpServletRequest request) {
        if (ServletFileUpload.isMultipartContent(request)) {
            //此时属于multipart/form类型
            ServletFileUpload fileUpload = new ServletFileUpload();
            try {
                FileItemIterator itemIterator = fileUpload.getItemIterator(request);
                while (itemIterator.hasNext()) {
                    FileItemStream fileItemStream = itemIterator.next();
                    InputStream inputStream = fileItemStream.openStream();
                    if (fileItemStream.isFormField()) {
                        //当前为表单域
                        setFormField(fileItemStream.getFieldName(), inputStream);
                    } else {
                        //当前为上传的文件
                        if (inputStream.available() > 0) {
                            //有数据才执行上传
                            String fileName = FilenameUtils.getName(fileItemStream.getName());
                            //完成文件流的拷贝，即完成上传
                            Streams.copy(inputStream, new FileOutputStream(UPLOADPATH + "/" + fileName), true);

                            /*FileOutputStream fileOutputStream = new FileOutputStream(UPLOADPATH + "/" + fileName);
                            byte[] buf = new byte[1024];
                            int len = -1;
                            while ((len = inputStream.read(buf)) != -1) {
                                //完成文件流的拷贝，即完成上传
                                fileOutputStream.write(buf, 0, len);
                            }*/

                            params.put(fileItemStream.getFieldName(), new String[] {fileName});
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //普通类型
            params = super.getParameterMap();
        }
    }

    private void setFormField(String fieldName, InputStream inputStream) throws IOException {
        if (params.containsKey(fieldName)) {
            String[] strings = params.get(fieldName);
            strings = Arrays.copyOf(strings, strings.length + 1);
            strings[strings.length - 1] = Streams.asString(inputStream);
            params.put(fieldName, strings);
        } else {
            params.put(fieldName, new String[]{Streams.asString(inputStream)});
        }
    }

    @Override
    public String getParameter(String name) {
        return params.get(name)[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }
}
