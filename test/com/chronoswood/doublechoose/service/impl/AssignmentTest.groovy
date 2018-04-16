package com.chronoswood.doublechoose.service.impl

import com.chronoswood.doublechoose.dao.AssignmentDao
import com.chronoswood.doublechoose.model.Will
import com.chronoswood.doublechoose.service.AssignmentService
import lombok.extern.slf4j.Slf4j
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
//@RunWith(SpringRunner.class)
@Slf4j
class AssignmentTest extends Specification {
    @Autowired
    private AssignmentDao assignmentDao

    def '插入数据'(){
        setup:
        Will will = new Will(
                projectName: 'abc',
                projectId: '100',
                previewImageURL: '',
                projectDescription: 'abc',
                directorId: '100',
                directorName: 'abc',
                studentId: '100',
                studentName: 'student',
                periodId: '100',
                projectBeginTime: LocalDateTime.now(),
                projectEndTime: LocalDateTime.now(),
                precedence: 1
        )
        int rowAffected = assignmentDao.insertOrUpdate([will])
        expect:
        rowAffected > 0
    }
}
