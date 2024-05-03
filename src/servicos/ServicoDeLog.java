package servicos;

import modelos.Log;

import java.util.HashMap;
import java.util.Map;

public class ServicoDeLog {
    private Map<Integer, Log> logHistory = new HashMap<>();

    public void addLog(Log log) {
        logHistory.put(log.getId(), log);
    }

    public Log searchLog(int id) {
        return logHistory.get(id);
    }

    public void displayAllLogs() {
        if(logHistory.size() == 0) {
            System.out.println("------------------------------------");
            System.out.println("Não há conversões para consultar log");
            System.out.println("------------------------------------");
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Logs de Conversões:");
        System.out.println("------------------------------------");
        for (Log log : logHistory.values()) {
            log.printLog();
        }
        System.out.println("------------------------------------");
    }
}
