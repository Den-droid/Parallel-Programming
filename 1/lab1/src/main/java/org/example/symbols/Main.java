package org.example.symbols;

public class Main {
    public static void main(String[] args) {
//        noSyncSymbol();
        syncSymbol();
    }

    public static void noSyncSymbol() {
        SymbolNoSyncThread symbolNoSyncThread = new SymbolNoSyncThread('-');
        SymbolNoSyncThread symbolNoSyncThread1 = new SymbolNoSyncThread('|');
        symbolNoSyncThread.start();
        symbolNoSyncThread1.start();
    }

    public static void syncSymbol() {
        Lock lock = new Lock(3);
        SymbolSyncThread symbolSyncThread = new SymbolSyncThread(lock, '-', 0);
        SymbolSyncThread symbolSyncThread1 = new SymbolSyncThread(lock, '|', 1);
        SymbolSyncThread symbolSyncThread2 = new SymbolSyncThread(lock, '/', 2);
        symbolSyncThread.start();
        symbolSyncThread1.start();
        symbolSyncThread2.start();
    }
}
