package com.ipsoflatus.dreamgifts.modelo.dao;

import java.util.List;

public interface SoftDeleteDao {
    
    void updateStateByIds(List<Integer> ids, boolean estado);
    
}
