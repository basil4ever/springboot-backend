package net.javaguides.springbootbackend.controller;


import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.model.File;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import net.javaguides.springbootbackend.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static final String DIRECTORY = System.getProperty("user.home") + "/IdeaProjects/Angular + Spring Boot CRUD Full Stack App/be-file-storage/";

    @PostMapping("/upload/employee/{id}")
    public ResponseEntity<List<String>> uploadFiles(@PathVariable Long id, @RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        Set<File> savedFiles = new HashSet<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);

            File newFile = new File();
            newFile.setName(filename);
            newFile.setPath(DIRECTORY);
            File savedFile = fileRepository.save(newFile);
            savedFiles.add(savedFile);


        }

        // Link files to employer
        Employee currentEmp = employeeRepository.getReferenceById(id);
        currentEmp.setFiles(savedFiles);
        employeeRepository.save(currentEmp);
        return ResponseEntity.ok().body(filenames);
    }

    @GetMapping("download/{id}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("id") Long fileId) throws IOException {
        File currentFile = fileRepository.getReferenceById(fileId);

        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(currentFile.getName());
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(currentFile.getName() + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", currentFile.getName());
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
}
