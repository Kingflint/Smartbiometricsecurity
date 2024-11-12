package com.flint.smartsecurity;

import java.util.List;

public interface IDataManager {
    List<AccessRecord> getAccessRecords();
    void close();
}
