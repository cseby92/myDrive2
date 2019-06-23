package com.sebastian.myDrive.web;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("files")
public class MyDriveWebService {

  @GetMapping
  public @ResponseBody String helloWorld() {
    return "HELLO WOOOORLD";
  }

  //CHUNKED???????? probably not the good way...
  @PostMapping(value ="upload")
  @ResponseStatus(value = HttpStatus.OK)
  public void multipartUpload(final HttpServletRequest request) throws IOException {
    String res = convertStreamToString(request.getInputStream(), StandardCharsets.UTF_8.name());
    System.out.println(res);
  }

  //USE MULTIPART INSTEAD
  private static String convertStreamToString( InputStream is, String ecoding ) throws IOException
  {
    StringBuilder sb = new StringBuilder( Math.max( 16, is.available() ) );
    char[] tmp = new char[ 4096 ];

    try {
      InputStreamReader reader = new InputStreamReader( is, ecoding );
      for( int cnt; ( cnt = reader.read( tmp ) ) > 0; )
        sb.append( tmp, 0, cnt );
    } finally {
      is.close();
    }
    return sb.toString();
  }
}
