package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class EnvController {

    private String PORT, MEMORY_LIMIT, CF_INSTANCE_INDEX, CF_INSTANCE_ADDR;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memory_limit,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String cf_instance_index,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cf_instance_addr) {
        PORT = port;
        MEMORY_LIMIT = memory_limit;
        CF_INSTANCE_INDEX = cf_instance_index;
        CF_INSTANCE_ADDR = cf_instance_addr;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        HashMap<String, String> keyMap = new HashMap<>();
        keyMap.put("PORT",PORT);
        keyMap.put("MEMORY_LIMIT",MEMORY_LIMIT);
        keyMap.put("CF_INSTANCE_INDEX",CF_INSTANCE_INDEX);
        keyMap.put("CF_INSTANCE_ADDR",CF_INSTANCE_ADDR);

        return keyMap;
    }
}
