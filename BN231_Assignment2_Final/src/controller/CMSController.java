package controller;

import model.Student;
import service.CMSService;

public class CMSController {

    private final CMSService cmsService;

    public CMSController(CMSService cmsService) {
        this.cmsService = cmsService;
    }

    public boolean addStudent(Student student) {
        return cmsService.registerStudent(student);
    }
}