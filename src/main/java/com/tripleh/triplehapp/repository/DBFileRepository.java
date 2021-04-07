package com.tripleh.triplehapp.repository;

import com.tripleh.triplehapp.entity.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
    public DBFile findByNombre(String imageName);

}
