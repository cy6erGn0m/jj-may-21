package ru.levelp.jj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.levelp.jj.model.FileUploadItem;

@Repository
public interface FileUploadItemRepository extends JpaRepository<FileUploadItem, Integer> {
}
