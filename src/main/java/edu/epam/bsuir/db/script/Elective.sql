DROP TABLE IF EXISTS Course;
CREATE TABLE Course (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    finished TINYINT(1) NOT NULL DEFAULT 0,
    startDate datetime NOT NULL,
    endDate datetime NOT NULL,
    lectorId int NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ID_UNIQUE (id),
    UNIQUE KEY lector_id_UNIQUE (lectorId),
    UNIQUE KEY course_UNIQUE (name),
    CONSTRAINT FK_COURSE_LECTOR FOREIGN KEY (lectorId) REFERENCES Lector (id)
);

DROP TABLE IF EXISTS Lector;
CREATE TABLE Lector (
    id int NOT NULL AUTO_INCREMENT,
    personalDataId int NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY id_UNIQUE (id),
    UNIQUE KEY login_UNIQUE (personalDataId),
    CONSTRAINT FK_LECTOR_PERSONAL_DATA FOREIGN KEY (personalDataId) REFERENCES PersonalData (id)
);

DROP TABLE IF EXISTS PersonalData;
CREATE TABLE PersonalData (
      id int NOT NULL AUTO_INCREMENT,
      login varchar(45) NOT NULL,
      password varchar(100) NOT NULL,
      PRIMARY KEY (id),
      UNIQUE KEY id_UNIQUE (id),
      UNIQUE KEY login_UNIQUE (login)
);

DROP TABLE IF EXISTS Semester;
CREATE TABLE Semester (
      courseId int NOT NULL,
      studentId int NOT NULL,
      mark int DEFAULT NULL,
      comment varchar(45) DEFAULT NULL,
      PRIMARY KEY (courseId, studentId),
      KEY FK_SEMESTER_STUDENT_idx (studentId),
      CONSTRAINT FK_SEMESTER_CURSE FOREIGN KEY (courseId) REFERENCES Course (id),
      CONSTRAINT FK_SEMESTER_STUDENT FOREIGN KEY (studentId) REFERENCES Student (id)
);

DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
     id int NOT NULL AUTO_INCREMENT,
     personalDataId int NOT NULL,
     avgMark double NOT NULL DEFAULT '0',
     PRIMARY KEY (id),
     UNIQUE KEY id_UNIQUE (id),
     UNIQUE KEY login_UNIQUE (personalDataId),
     CONSTRAINT FK_STUDENT_PERSONAL_DATA FOREIGN KEY (personalDataId) REFERENCES PersonalData (id)
);
