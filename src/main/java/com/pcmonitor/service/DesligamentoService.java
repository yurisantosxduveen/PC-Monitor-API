package com.pcmonitor.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DesligamentoService {

    public void desligarComputador() throws IOException {

        new ProcessBuilder("shutdown", "/s", "/t", "60").start();


    }

    public void cancelarDesligamento () throws IOException {

        new ProcessBuilder("shutdown", "/a").start();


    }


}
