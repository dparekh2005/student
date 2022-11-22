
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO groups(group_id,group_name) VALUES (101,'GROUP-A');
INSERT INTO teacher(teacher_id,teacher_name) VALUES (201, 'Ajit');
INSERT INTO subject(subject_id,title) VALUES (301,'Maths');
INSERT INTO student(student_id, first_name, last_name, group_id) VALUES (401,'Rajat','Shah',101);
INSERT INTO marks(mark_id,student_id, subject_id, date, mark) VALUES (501,401,301,'2022-02-02',68);
INSERT INTO group_teacher(group_id,teacher_id) VALUES (101,201);
INSERT INTO subject_teacher(subject_id,teacher_id) VALUES (301,201);

/*INSERT INTO subject_teacher(subject_id,teacher_id,group_id) VALUES (301,201,101);*/
