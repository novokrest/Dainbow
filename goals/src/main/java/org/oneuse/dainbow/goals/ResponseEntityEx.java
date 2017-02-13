package org.oneuse.dainbow.goals;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

public class ResponseEntityEx {
    public static <T> ResponseEntity<T> redirect(String location) {
        try {
            return redirect(new URI(location));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ResponseEntity<T> redirect(URI location) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
