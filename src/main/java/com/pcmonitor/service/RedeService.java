package com.pcmonitor.service;

import com.pcmonitor.dto.RedeDTO;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

@Service
public class RedeService {

    private final SystemInfo systemInfo =  new SystemInfo();

    public RedeDTO consultarRede() {

        for (NetworkIF rede : systemInfo.getHardware().getNetworkIFs()) {

            rede.updateAttributes();

            String[] ips = rede.getIPv4addr();

            if (rede.getIfOperStatus().toString().equalsIgnoreCase("UP")
                && ips.length > 0) {

                return new RedeDTO(
                        rede.getDisplayName(),
                        "Não informado",
                        rede.getMacaddr(),
                        ips[0],
                        true
                );
            }


        }
        return new RedeDTO(
                "Nenhuma interface ativa",
                "Não informado",
                "",
                "",
                false
        );
    }

}
