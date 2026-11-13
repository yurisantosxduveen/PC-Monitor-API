
package com.pcmonitor.service;

import com.pcmonitor.dto.ArmazenamentoDTO;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArmazenamentoService {

    public List<ArmazenamentoDTO> buscarArmazenamentos() {

        SystemInfo sistema = new SystemInfo();

        FileSystem sistemaArquivos =
                sistema.getOperatingSystem().getFileSystem();

        List<OSFileStore> discos =
                sistemaArquivos.getFileStores();

        List<ArmazenamentoDTO> armazenamento = new ArrayList<>();

        for (OSFileStore disco : discos) {

            long totalBytes = disco.getTotalSpace();

            long disponivelBytes = disco.getUsableSpace();

            if (totalBytes <= 0) {
                continue;
            }

            long utilizadoBytes = totalBytes - disponivelBytes;

            double totalGB =
                    totalBytes / (1024.0 * 1024 * 1024);

            double disponivelGB =
                    disponivelBytes / (1024.0 * 1024 * 1024);

            double utilizadoGB =
                    utilizadoBytes / (1024.0 * 1024 * 1024);

            double percentualUso =
                    (utilizadoBytes * 100.0) / totalBytes;

            armazenamento.add(new ArmazenamentoDTO(
                    disco.getName(),
                    disco.getMount(),
                    Math.round(totalGB * 100.0) / 100.0,
                    Math.round(disponivelGB * 100.0) / 100.0,
                    Math.round(utilizadoGB * 100.0) / 100.0,
                    Math.round(percentualUso * 100.0) / 100.0,
                    "GB"
            ));
        }

        return armazenamento;
    }
}
