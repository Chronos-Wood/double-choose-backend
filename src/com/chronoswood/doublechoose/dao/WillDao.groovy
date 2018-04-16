package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Will
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface WillDao {
    @Select('''
SELECT
  will.id as id,
  project.id as projectId,
  project.name as projectName,
  project.preview_iamge as previewImageURL,
  project.description as projectDescription,
  director.id as directorId,
  director.name as directorName,
  student.id as studentId,
  student.name as studentName,
  project.period_id as periodId,
  project.begin as projectBeginTime,
  project.end as projectEndTime,
  project.create_time as createTime,
  project.update_time as updateTime,
  will.accepted as accepted,
  will.precedence as precedence
FROM will
  JOIN project ON project.id=will.project_id
  JOIN period ON (will.period_id=period.id and NOW() >= period.begin and NOW() <= period.end)
  JOIN student ON student.id = will.student_id
  JOIN director ON ( project.director_id = director.id and director.user_name = #{directorUsername})
WHERE project_id=#{projectId}
ORDER BY will.update_time DESC 
LIMIT 3;
''')
    List<Will> queryWillByDirectorUsernameAndProjectId(@Param('projectId') String projectId,
                                                       @Param('directorUsername') String directorUsername,
                                                       @Param('offset') int offset,
                                                       @Param('amount') int amount);



    @Select('''
SELECT
  will.id as id,
  project.id as projectId,
  project.name as projectName,
  project.preview_iamge as previewImageURL,
  project.description as projectDescription,
  director.id as directorId,
  director.name as directorName,
  student.id as studentId,
  student.name as studentName,
  project.period_id as periodId,
  project.begin as projectBeginTime,
  project.end as projectEndTime,
  project.create_time as createTime,
  project.update_time as updateTime,
  will.accepted as accepted,
  will.precedence as precedence
FROM will
   LEFT JOIN project ON project.id=will.project_id 
   LEFT JOIN period on (will.period_id=period.id and NOW() >= period.begin and NOW() <= period.end) 
   left join student on( student_id = will.student_id and student.user_name=#{studentUserName})
   left join director on director.id = project.director_id
  ORDER BY will.precedence, will.update_time limit 3
''')
    List<Will> queryWillsByStudentUserName(@Param('studentUserName') String studentUserName);

    @Select('''
SELECT
  will.id as id,
  project.id as projectId,
  project.name as projectName,
  project.preview_iamge as previewImageURL,
  project.description as projectDescription,
  director.id as directorId,
  director.name as directorName,
  student.id as studentId,
  student.name as studentName,
  project.period_id as periodId,
  project.begin as projectBeginTime,
  project.end as projectEndTime,
  project.create_time as createTime,
  project.update_time as updateTime,
  will.accepted as accepted,
  will.precedence as precedence
FROM will
  JOIN project ON (project.id=will.project_id AND project.id=#{projectId})
  JOIN period ON (will.period_id=period.id and NOW() >= period.begin and NOW() <= period.end)
  JOIN student ON student.id = will.student_id
  JOIN director ON project.director_id = director.id
WHERE will.accepted=1
ORDER BY (will.precedence, will.update_time) limit 3;
''')
    List<Will> queryAcceptedWillsByProjectId(String projectId);

    @Select('SELECT * FROM will WHERE id=#{id}')
    Will queryWillById(String id);

    int storeWill(@Param("wills")List<Will> wills)

    int acceptWill(@Param('directorUserName') String directorUserName, @Param('willIds') List<String> willIds)

    @Select('''
SELECT
  will.id as id,
  project.id as projectId,
  project.name as projectName,
  project.preview_iamge as previewImageURL,
  project.description as projectDescription,
  director.id as directorId,
  director.name as directorName,
  student.id as studentId,
  student.name as studentName,
  project.period_id as periodId,
  project.begin as projectBeginTime,
  project.end as projectEndTime,
  project.create_time as createTime,
  project.update_time as updateTime,
  will.accepted as accepted,
  will.precedence as precedence
FROM will
  JOIN project ON project.id=will.project_id
  JOIN period ON (will.period_id=period.id and NOW() >= period.begin and NOW() <= period.end)
  JOIN student ON student.id = will.student_id
  JOIN director ON project.director_id = director.id
WHERE will.accepted=1
ORDER BY (will.precedence, will.update_time)
LIMIT #{offseet}, #{amount} 
''')
    List<Will> queryAcceptedWill(@Param('offset') offset, @Param('amount') amount);

}
