package com.lewatihari.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nullable;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemErrorException extends RuntimeException {
    public SystemErrorException(@Nullable String e) {
        super(e != null ? e : "Terjadi Kesalahan Pada Sistem");
    }

}