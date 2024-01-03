package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IClassCourseDAO;
import com.laptrinhjavaWeb.dao.IRegisterRoomDAO;
import com.laptrinhjavaWeb.dao.IRoomDAO;
import com.laptrinhjavaWeb.dao.IWeekStudyDAO;
import com.laptrinhjavaWeb.mapper.ClassCourseMapper;
import com.laptrinhjavaWeb.model.ClassCourseModel;
import com.laptrinhjavaWeb.model.RegisterRoomModel;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.WeekStudyModel;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClassCourseDAO extends AbstractDAO<ClassCourseModel> implements IClassCourseDAO {
    @Inject
    private IRoomDAO roomDAO;
    @Inject
    private IWeekStudyDAO weekStudyDAO;
    @Inject
    private IRegisterRoomDAO registerRoomDAO;
    @Override
    public Long insert(ClassCourseModel classCourseModel) {
        String sql="insert into classcourse(code,startLesson,endLesson,startWeek,endWeek,slotRoom,createBy) values(?,?,?,?,?,?,?);";
        Long id=insert(sql,classCourseModel.getCode(),classCourseModel.getStartLesson(),classCourseModel.getEndLesson(),classCourseModel.getStartWeek(),classCourseModel.getEndWeek(),classCourseModel.getSlotRoom(),classCourseModel.getCreateBy());
        if(id!=-1L){
            // add into listclass couse
            sql="insert into listclasscourse(CourseCode,ClassCourseCode,createBy) values(?,?,?);";
            if(insert(sql,classCourseModel.getCourseCode(),classCourseModel.getCode(),classCourseModel.getCreateBy())==-1L)return -1L;
            //add into listclasscourseroom
//            if(classCourseModel.getAreaCode()!=null&&classCourseModel.getNumberRoomCode()!=null){
//                sql="insert into listclasscourseroom(ClassCourseCode,AreaCode,NumberRoomCode,createBy) values(?,?,?,?);";
//                if(insert(sql,classCourseModel.getCode(),classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode(),classCourseModel.getCreateBy())==-1L)return -1L;
//            }
            if(classCourseModel.getTeacherCode()!=null){
                sql="insert into listteacherclasscourse(ClassCourseCode,TeacherCode,createBy) values(?,?,?);";
                if(insert(sql,classCourseModel.getCode(),classCourseModel.getTeacherCode(),classCourseModel.getCreateBy())==-1L)return -1L;
            }
        }else return -1L;
        return id;
    }

    @Override
    public Long insertHistoryClassCourse(ClassCourseModel classCourseModel) {
        String sql="insert into historyregistercourse(ClassCourseCode,StudentCode,createBy) values(?,?,?);";
        Long id=insert(sql,classCourseModel.getCode(),classCourseModel.getStudentCode(),classCourseModel.getCreateBy());
        if(id==-1L)return -1L;
        return id;
    }

    @Override
    public ClassCourseModel findAllRegisterClassCourseByStudentCode(String studentCode) {
        // lấy danh sách đăng ký học phần nhưng chưa học
        String sql="SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "    listteacherclasscourse.id AS TeacherId,\n" +
                "    teacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    listclasscourseroom.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat AS RoomSeat,\n" +
                "    course.name,\n" +
                "    course.code AS CourseCode,\n" +
                "    course.id AS CourseId,\n" +
                "    course.numberCredit AS numberCredit\n" +
                "FROM\n" +
                "    historyregistercourse\n" +
                "INNER JOIN\n" +
                "    listclasscourse ON historyregistercourse.ClassCourseCode = listclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN\n" +
                "    course ON listclasscourse.CourseCode = course.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON listclasscourse.ClassCourseCode = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "WHERE\n" +
                "    historyregistercourse.StudentCode = ?\n" +
                "    AND NOT EXISTS (\n" +
                "        SELECT 1\n" +
                "        FROM resultstudycourse\n" +
                "        WHERE resultstudycourse.ClassCourseCode = historyregistercourse.ClassCourseCode\n" +
                "          AND resultstudycourse.StudentCode = historyregistercourse.StudentCode\n" +
                "    )\n" +
                "ORDER BY\n" +
                "    course.code ASC;";
        ClassCourseModel classCourseModel=new ClassCourseModel();
           classCourseModel.setList(query(sql,new ClassCourseMapper(),studentCode));
           return classCourseModel;
    }

    @Override
    public ClassCourseModel findClassCourseStudied(ClassCourseModel classCourseModel) {
        String sql="select resultstudycourse.ClassCourseCode as code\n" +
                "from resultstudycourse\n" +
                "inner join listclasscourse on resultstudycourse.ClassCourseCode=listclasscourse.ClassCourseCode\n" +
                "where listclasscourse.CourseCode=? and resultstudycourse.StudentCode=?;";
        List<ClassCourseModel>classCourseModels=query(sql,new ClassCourseMapper(),classCourseModel.getCourseCode(),classCourseModel.getStudentCode());
        if(classCourseModels!=null&&!classCourseModels.isEmpty()){
            return classCourseModels.get(0);
        }
        return null;
    }

    @Override
    public ClassCourseModel findAllStudentInClassCouse(String classcourseCode) {
        String sql="SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "    listteacherclasscourse.id AS TeacherId,\n" +
                "    teacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    listclasscourseroom.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat as RoomSeat,\n" +
                "    course.name,\n" +
                "    course.code as CourseCode,\n" +
                "    course.id as CourseId,\n" +
                "    course.numberCredit as numberCredit,\n" +
                "    student.code as StudentCode\n" +
                "FROM \n" +
                "    historyregistercourse\n" +
                "INNER JOIN \n" +
                "    listclasscourse ON historyregistercourse.ClassCourseCode = listclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN \n" +
                "    course on listclasscourse.CourseCode = course.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "INNER JOIN \n" +
                "    student on historyregistercourse.StudentCode = student.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON listclasscourse.ClassCourseCode = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "WHERE\n" +
                "    historyregistercourse.ClassCourseCode = ?;";
        ClassCourseModel classCourseModel=new ClassCourseModel();
        List<ClassCourseModel>list=query(sql,new ClassCourseMapper(),classcourseCode);
        if(list!=null&&!list.isEmpty()){
            classCourseModel=list.get(0);
            classCourseModel.setList(list);
        }
        return classCourseModel;
    }

    @Override
    public ClassCourseModel findById(Long id) {
        String sql= "SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "    listteacherclasscourse.id AS TeacherId,\n" +
                "    teacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    listclasscourseroom.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat as RoomSeat,\n" +
                "    course.name as name,\n" +
                "    course.id as CourseId,\n" +
                "    course.code as CourseCode,\n" +
                "    course.numberCredit as numberCredit\n" +
                "FROM\n" +
                "    course\n" +
                "INNER JOIN\n" +
                "    listclasscourse ON course.code = listclasscourse.CourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON listclasscourse.ClassCourseCode = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "WHERE\n" +
                "    classcourse.id = ?\n" +
                "ORDER BY\n" +
                "    course.code ASC;";
        return findById(sql,new ClassCourseMapper(),id);
    }

    @Override
    public ClassCourseModel findByCode(String code) {
        String sql="SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "    listteacherclasscourse.id AS TeacherId,\n" +
                "    teacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    listclasscourseroom.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat as RoomSeat,\n" +
                "    course.name as name,\n" +
                "    course.id as CourseId,\n" +
                "    course.code as CourseCode,\n" +
                "    course.numberCredit as numberCredit\n" +
                "FROM\n" +
                "    course\n" +
                "INNER JOIN\n" +
                "    listclasscourse ON course.code = listclasscourse.CourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON listclasscourse.ClassCourseCode = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "WHERE\n" +
                "    classcourse.code = ?\n" +
                "ORDER BY\n" +
                "    course.code ASC;";
        return findByCode(sql,new ClassCourseMapper(),code);
    }

    @Override
    public ClassCourseModel findAllByCourseCode(String code) {
        String sql="SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "    teacher.id AS TeacherId,\n" +
                "    teacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    room.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat AS RoomSeat\n" +
                "FROM\n" +
                "    course\n" +
                "INNER JOIN\n" +
                "    listclasscourse ON course.code = listclasscourse.CourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON classcourse.code = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "WHERE\n" +
                "    course.code = ?\n" +
                "ORDER BY\n" +
                "    course.code ASC;\n";
        List<ClassCourseModel>classCourseModels=query(sql,new ClassCourseMapper(),code);
        ClassCourseModel classCourseModel=new ClassCourseModel();
        classCourseModel.setList(classCourseModels);
        classCourseModel.setCourseCode(code);
        return classCourseModel;
    }

    @Override
    public List<ClassCourseModel> findAll() {
        String sql="SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "    listteacherclasscourse.id AS TeacherId,\n" +
                "    teacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    listclasscourseroom.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat as RoomSeat,\n" +
                "    course.name as name,\n" +
                "    course.id as CourseId,\n" +
                "    course.code as CourseCode,\n" +
                "    course.numberCredit as numberCredit\n" +
                "FROM\n" +
                "    course\n" +
                "INNER JOIN\n" +
                "    listclasscourse ON course.code = listclasscourse.CourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON listclasscourse.ClassCourseCode = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "ORDER BY\n" +
                "    course.code ASC;\n";
        // lấy ra số lượng hsinh đăng ký trong từng classcourse
        List<ClassCourseModel>list=query(sql,new ClassCourseMapper());
        if(list!=null&&!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                ClassCourseModel model=findAllStudentInClassCouse(list.get(i).getCode());
                list.get(i).setList(model.getList());// mỗi classcourse sẽ có 1 danh sách classcourse chứa 1 studentCode
            }
            return list;
        }
        return null;
    }

    @Override
    public Long update(ClassCourseModel classCourseModel) {
        //update listclasscourseroom
        try{
            ClassCourseModel oldClassCourse=findByCode(classCourseModel.getCode());
            String sql="insert into listclasscourseroom(ClassCourseCode,AreaCode,NumberRoomCode,createBy) values(?,?,?,?)";
            if(oldClassCourse.getRoomModel()==null||(oldClassCourse.getRoomModel().getAreaCode()==null&&oldClassCourse.getRoomModel().getRoomCode()==null)){
                int seat=roomDAO.findByCode(classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode()).getSeat();
                if(classCourseModel.getSlotRoom()<=seat){
                    // thêm phòng học
                    if(insert(sql,classCourseModel.getCode(),classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode(),classCourseModel.getModifiedBy())==-1L)return -1L;
                    //thêm phòng học thành công thì lưu tự dđộng vào hệ thong thoi gian hoc
                    RoomModel roomModel=roomDAO.findByCode(classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode());
                    classCourseModel.setRoomModel(roomModel);
                    if(!registerRoom(classCourseModel)){
                        System.out.println("Đăng ký lớp thất bại "+classCourseModel.getCode());
                    }
                    oldClassCourse=findByCode(classCourseModel.getCode());
                }else{

                    return -2L;// không đủ chỗ ngồi cho phòng
                }
            }
                //update
                //code classCourse equal old classcourse
                if(oldClassCourse.getCode().equals(classCourseModel.getCode())){
                    //update classcourse
                    sql="update classcourse set dayOnWeek=?,startLesson=?,endLesson=?,startWeek=?,endWeek=?,slotRoom=?,modifiedBy=?,modifiedDate=? where id=?";
                    if(update(sql,classCourseModel.getDayOnWeek(),classCourseModel.getStartLesson(),classCourseModel.getEndLesson(),classCourseModel.getStartWeek(),classCourseModel.getEndWeek(),classCourseModel.getSlotRoom(),classCourseModel.getModifiedBy(),classCourseModel.getModifiedDate(),classCourseModel.getId())==-1L)return -1L;

                    //update teacher class course
                    if(!oldClassCourse.getTeacherModel().getCode().equals(classCourseModel.getTeacherCode())){
                        sql="update listteacherclasscourse set ClassCourseCode=?,TeacherCode=?,modifiedBy=?,modifiedDate=? where id=?";
                        if(update(sql,classCourseModel.getCode(),classCourseModel.getTeacherCode(),classCourseModel.getModifiedDate(),classCourseModel.getModifiedBy(),oldClassCourse.getTeacherModel().getId())==-1L)return -1L;
                    }

                    //cập nhật phòng học khi thay đổi phòng
                    if(!oldClassCourse.getRoomModel().getAreaCode().equals(classCourseModel.getAreaCode())||!oldClassCourse.getRoomModel().getRoomCode().equals(classCourseModel.getRoomCode())){
                        int seat=roomDAO.findByCode(classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode()).getSeat();
                        if(classCourseModel.getSlotRoom()<=seat){
                            sql="update listclasscourseroom set ClassCourseCode=?,AreaCode=?,NumberRoomCode=?,modifiedBy=?,modifiedDate=? where id=?";
                            if(update(sql,classCourseModel.getCode(),classCourseModel.getAreaCode(),classCourseModel.getNumberRoomCode(),classCourseModel.getModifiedBy(),classCourseModel.getModifiedDate(),oldClassCourse.getRoomModel().getId())==-1L)return -1L;
                            //cập nhật đăng ký phòng học sau khi thay đổi mã học lớp học phần
                        }
                        else return -1L;
                    }
                }
                else{

                    //update classcourse
                    //update teacher class course
                    //update classcourse room
                }
        }catch (Exception e){
         e.printStackTrace();
            return -1L;
        }
        return 1L;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long deleteHistoryRegisterClassCourse(ClassCourseModel classCourseModel) {
        String sql="delete from historyregistercourse where ClassCourseCode=? and StudentCode=?;";
        return update(sql,classCourseModel.getCode(),classCourseModel.getStudentCode());
    }

    @Override
    public ClassCourseModel findAllByTeacherCode(String teacherCode) {
        String sql="SELECT\n" +
                "    classcourse.*,\n" +
                "    listclasscourse.CourseCode AS CourseCode,\n" +
                "\tlistteacherclasscourse.id AS TeacherId,\n" +
                "\tteacher.code AS TeacherCode,\n" +
                "    teacher.fullname AS TeacherName,\n" +
                "    listclasscourseroom.id AS RoomId,\n" +
                "    room.Area AS RoomArea,\n" +
                "    room.code AS RoomCode,\n" +
                "    room.seat AS RoomSeat,\n" +
                "    course.name AS name,\n" +
                "    course.id AS CourseId,\n" +
                "    course.code AS CourseCode,\n" +
                "    course.numberCredit AS numberCredit\n" +
                "FROM\n" +
                "    course\n" +
                "INNER JOIN\n" +
                "    listclasscourse ON course.code = listclasscourse.CourseCode\n" +
                "INNER JOIN\n" +
                "    classcourse ON listclasscourse.ClassCourseCode = classcourse.code\n" +
                "INNER JOIN\n" +
                "    listteacherclasscourse ON classcourse.code = listteacherclasscourse.ClassCourseCode\n" +
                "INNER JOIN\n" +
                "    teacher ON listteacherclasscourse.TeacherCode = teacher.code\n" +
                "LEFT JOIN\n" +
                "    listclasscourseroom ON listclasscourse.ClassCourseCode = listclasscourseroom.ClassCourseCode\n" +
                "LEFT JOIN\n" +
                "    room ON listclasscourseroom.AreaCode = room.Area AND listclasscourseroom.NumberRoomCode = room.code\n" +
                "WHERE\n" +
                "    listteacherclasscourse.TeacherCode = ?\n" +
                "ORDER BY\n" +
                "    classcourse.id ASC;";
        ClassCourseModel classCourseModel=new ClassCourseModel();
        classCourseModel.setList(query(sql,new ClassCourseMapper(),teacherCode));
        return classCourseModel;
    }

    public boolean registerRoom(ClassCourseModel classCourseModel){
        int dayOfWeek = classCourseModel.getDayOnWeek();
        dayOfWeek--;
        if (dayOfWeek == 0)dayOfWeek = 7;
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        String sql="insert into registerroom(RoomId,date,lesson,ClassCourseCode) values(?,?,?,?);";
        WeekStudyModel weekStudyModel=weekStudyDAO.findDateWeekDurationInYear(classCourseModel.getStartWeek(),classCourseModel.getEndWeek(),year);
        LocalDate startDate = LocalDate.of(weekStudyModel.getStartWeekStudy().getDate().getYear(), weekStudyModel.getStartWeekStudy().getDate().getMonthValue(),weekStudyModel.getStartWeekStudy().getDate().getDayOfMonth());
        // Khởi tạo ngày kết thúc

        LocalDate endDate = LocalDate.of(weekStudyModel.getEndWeekStudy().getDate().getYear(), weekStudyModel.getEndWeekStudy().getDate().getMonthValue(),weekStudyModel.getEndWeekStudy().getDate().getDayOfMonth());
        for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date = date.plusDays(1)) {
            // Lấy thứ của ngày hiện tại
            DayOfWeek dayOfWeekCurrent = date.getDayOfWeek();

            // Kiểm tra xem ngày hiện tại có phải là ngày trong tuần đã nhập không
            if (dayOfWeekCurrent == DayOfWeek.of(dayOfWeek)) {
                // Nếu đúng, in ra ngày hiện tại
                for(int i=classCourseModel.getStartLesson();i<classCourseModel.getEndLesson();i++){
                    if(insert(sql,classCourseModel.getRoomModel().getId(),java.sql.Date.valueOf(date),i,classCourseModel.getCode())==-1){
//                        System.out.println("Đăng ký lớp học phần "+classCourseModel.getCode()+" vào "+date+" lỗi");
                    }
                }
            }
        }
        return true;
    }

}
