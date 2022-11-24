CREATE
DATABASE SHOPQUANAO;
USE
SHOPQUANAO;
GO

CREATE TABLE THONGTINSP
(
    MASP    VARCHAR(10) NOT NULL,
    TENSP   NVARCHAR(100),
    HANGSX  VARCHAR(20),
    lOAI    VARCHAR(20),
    GIA     DOUBLE,
    GIAMGIA INT,
    MOTA    NVARCHAR(2000)
);

CREATE TABLE CTSP
(
    MASP   VARCHAR(10),
    MAUSAC VARCHAR(20),
    IMGURL VARCHAR(255)
);

CREATE TABLE KHOHANG
(
    MASP    VARCHAR(10),
    SIZE    VARCHAR(10),
    MAUSAC  NVARCHAR(10),
    SOLUONG INT
);

CREATE TABLE CTHD
(
    MAHD    VARCHAR(20),
    MASP    VARCHAR(10),
    SIZE    VARCHAR(10),
    MAUSAC  NVARCHAR(10),
    SOLUONG INT
);

CREATE TABLE HOADON
(
    MAHD       VARCHAR(20) NOT NULL,
    MANV       VARCHAR(20),
    MAKH       VARCHAR(20),
    NGAYXUATHD DATE,
    TRIGIA     BIGINT
);

CREATE TABLE KHACHHANG
(
    MAKH   VARCHAR(20) NOT NULL,
    TENKH  NVARCHAR(100),
    DIACHI NVARCHAR(255),
    SDT    VARCHAR(12),
    EMAIL  VARCHAR(100)
);

CREATE TABLE NHANVIEN
(
    MANV   VARCHAR(20) NOT NULL,
    TENNV  NVARCHAR(100),
    DIACHI NVARCHAR(255),
    SDT    VARCHAR(12),
    EMAIL  VARCHAR(100),
    NGAYVL DATE
);

CREATE TABLE GIOHANG
(
    MAGH            INT      NOT NULL AUTO_INCREMENT,
    MAKH            VARCHAR(20),
    TRANGTHAI       TINYINT  NOT NULL,
    THOIGIANTAO     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    THOIGIANCAPNHAT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (MAGH)
);

CREATE TABLE CTGH
(
    ID              INT         NOT NULL AUTO_INCREMENT,
    MAGH            INT         NOT NULL,
    MASP            VARCHAR(10) NOT NULL,
    GIA             DOUBLE      NOT NULL,
    SOLUONG         INT         NOT NULL,
    THOIGIANTAO     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    THOIGIANCAPNHAT DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE TAIKHOAN
(
    TENTK   VARCHAR(100) NOT NULL,
    MATKHAU INT(100) NOT NULL,
    QUYEN   INT          NOT NULL DEFAULT '0',
    HO      VARCHAR(50)  NOT NULL,
    TEN     VARCHAR(50)  NOT NULL,
    EMAIL   VARCHAR(255) NOT NULL,
    SDT     VARCHAR(20)  NOT NULL,
    PRIMARY KEY (TENTK)
);

CREATE TABLE QUENMK
(
    ID         INT          NOT NULL,
    EMAIL      VARCHAR(255) NOT NULL,
    NGAYHETHAN DATETIME     NOT NULL
);

-- THEM KHOA CHINH --
ALTER TABLE THONGTINSP
    ADD CONSTRAINT MASP_PK PRIMARY KEY (MASP);
ALTER TABLE HOADON
    ADD CONSTRAINT MAHD_PK PRIMARY KEY (MAHD);
ALTER TABLE NHANVIEN
    ADD CONSTRAINT NV_PK PRIMARY KEY (MANV);
ALTER TABLE KHACHHANG
    ADD CONSTRAINT KH_PK PRIMARY KEY (MAKH);

-- THEM KHOA PHU --
ALTER TABLE CTSP
    ADD CONSTRAINT CTSP_FK FOREIGN KEY (MASP) REFERENCES THONGTINSP (MASP);
ALTER TABLE KHOHANG
    ADD CONSTRAINT KH_FK FOREIGN KEY (MASP) REFERENCES THONGTINSP (MASP);
ALTER TABLE CTHD
    ADD CONSTRAINT CTHD_FK_CTSP FOREIGN KEY (MASP) REFERENCES THONGTINSP (MASP);
ALTER TABLE CTHD
    ADD CONSTRAINT CTHD_FK_HD FOREIGN KEY (MAHD) REFERENCES HOADON (MAHD);
ALTER TABLE HOADON
    ADD CONSTRAINT HD_FK_NV FOREIGN KEY (MANV) REFERENCES NHANVIEN (MANV);
ALTER TABLE HOADON
    ADD CONSTRAINT HD_FK_KH FOREIGN KEY (MAKH) REFERENCES KHACHHANG (MAKH);
ALTER TABLE GIOHANG
    ADD CONSTRAINT GH_FK_KH FOREIGN KEY (MAKH) REFERENCES KHACHHANG (MAKH);
ALTER TABLE CTGH
    ADD CONSTRAINT CTGH_FK_GH FOREIGN KEY (MAGH) REFERENCES GIOHANG (MAGH);

-- THEM SAN PHAM AO THUN
INSERT INTO THONGTINSP
VALUES ('SP01', N'ÁO THUN IN KICK BACK AT057', 'NIKE', N'ÁO THUN IN KICK BACK AT057');
INSERT INTO THONGTINSP
VALUES ('SP02', N'ÁO THUN TRƠN VẢI ĐỐM AT044', 'NIKE', N'ÁO THUN TRƠN VẢI ĐỐM AT044');
INSERT INTO THONGTINSP
VALUES ('SP03', N'ÁO THUN TRƠN VẢI SỚ GỖ', 'NIKE', N'ÁO THUN TRƠN VẢI SỚ GỖ');
INSERT INTO THONGTINSP
VALUES ('SP04', N'ÁO THUN IN BASIC 4MEN AT053', 'NIKE', N'ÁO THUN IN BASIC 4MEN AT053');
INSERT INTO THONGTINSP
VALUES ('SP05', N'ÁO THUN THÊU HITTIN THE ROAD AT052', 'NIKE', N'ÁO THUN THÊU HITTIN THE ROAD AT052');
INSERT INTO THONGTINSP
VALUES ('SP06', N'ÁO THUN TRƠN CĂN BẢN MÀU BÒ AT051', 'NIKE', N'ÁO THUN TRƠN CĂN BẢN MÀU BÒ AT051');
INSERT INTO THONGTINSP
VALUES ('SP07', N'ÁO THUN IN FULL OF HOPE', 'NIKE', N'ÁO THUN IN FULL OF HOPE');
INSERT INTO THONGTINSP
VALUES ('SP08', N'ÁO THUN GẮN NHÃN MẶT CƯỜI', 'NIKE', N'ÁO THUN GẮN NHÃN MẶT CƯỜI');
INSERT INTO THONGTINSP
VALUES ('SP09', N'ÁO THUN SỌC TÚI THÊU LÁ', 'NIKE', N'ÁO THUN SỌC TÚI THÊU LÁ');
INSERT INTO THONGTINSP
VALUES ('SP10', N'ÁO THUN TRƠN SLIMFIT MÀU BÒ AT046', 'NIKE', N'ÁO THUN TRƠN SLIMFIT MÀU BÒ AT046');
INSERT INTO THONGTINSP
VALUES ('SP11', N'ÁO THU IN SIMPLICITY', 'ADIDAS', N'ÁO THU IN SIMPLICITY');
INSERT INTO THONGTINSP
VALUES ('SP12', N'ÁO THUN TRƠN FORM REGULAR', 'ADIDAS', N'ÁO THUN TRƠN FORM REGULAR');
INSERT INTO THONGTINSP
VALUES ('SP13', N'ÁO THUN TAY DÀI THÊU WORKS AT039', 'ADIDAS', N'ÁO THUN TAY DÀI THÊU WORKS AT039');
INSERT INTO THONGTINSP
VALUES ('SP14', N'ÁO THUN GAME OVER AT040', 'ADIDAS', N'ÁO THUN GAME OVER AT040');
INSERT INTO THONGTINSP
VALUES ('SP15', N'ÁO THUN ABSTRACT SHAPES AT033', 'ADIDAS', N'ÁO THUN ABSTRACT SHAPES AT033');
INSERT INTO THONGTINSP
VALUES ('SP16', N'ÁO THUN PHỐI ĐẮP AT032', 'ADIDAS', N'ÁO THUN PHỐI ĐẮP AT032');
INSERT INTO THONGTINSP
VALUES ('SP17', N'ÁO THUN ACID WASH AT028', 'ADIDAS', N'ÁO THUN ACID WASH AT028');
INSERT INTO THONGTINSP
VALUES ('SP18', N'ÁO THUN PHỐI RAGLAN GẮN NHÃN AT036', 'ADIDAS', N'ÁO THUN PHỐI RAGLAN GẮN NHÃN AT036');
INSERT INTO THONGTINSP
VALUES ('SP19', N'ÁO THUN PHỐI TÚI ĐẮP GẮN NHÃN AT035', 'ADIDAS', N'ÁO THUN PHỐI TÚI ĐẮP GẮN NHÃN AT035');
INSERT INTO THONGTINSP
VALUES ('SP20', N'ÁO THUN IN HÌNH VÀNG AT031', 'ADIDAS', N'ÁO THUN IN HÌNH VÀNG AT031');
INSERT INTO THONGTINSP
VALUES ('SP21', N'ÁO THUN IN ATTITUDE AT030', 'OWEN', N'ÁO THUN IN ATTITUDE AT030');
INSERT INTO THONGTINSP
VALUES ('SP22', N'ÁO THUN IN MINIMALIST AT029', 'OWEN', N'ÁO THUN IN MINIMALIST AT029');
INSERT INTO THONGTINSP
VALUES ('SP23', N'ÁO THUN TÚI THÊU CHỮ C AT034', 'OWEN', N'ÁO THUN TÚI THÊU CHỮ C AT034');
INSERT INTO THONGTINSP
VALUES ('SP24', N'ÁO THUN TRƠN OVERSIZE CÓ TÚI AT027', 'OWEN', N'ÁO THUN TRƠN OVERSIZE CÓ TÚI AT027');
INSERT INTO THONGTINSP
VALUES ('SP25', N'ÁO THUN CỔ HENLEY PHỐI DÂY DỆT AT026', 'OWEN', N'ÁO THUN CỔ HENLEY PHỐI DÂY DỆT AT026');

-- THEM SAN PHAM AO SO MI
INSERT INTO THONGTINSP
VALUES ('SP26', N'ASM NAZAFU SỌC PHỐI TRẮNG', 'OWEN', N'ASM NAZAFU SỌC PHỐI TRẮNG');
INSERT INTO THONGTINSP
VALUES ('SP27', N'ÁO SƠ MI TRƠN OXFORD ASM070', 'OWEN', N'ÁO SƠ MI TRƠN OXFORD ASM070');
INSERT INTO THONGTINSP
VALUES ('SP28', N'ÁO SƠ MI HỌA TIẾT XÁM SM058', 'OWEN', N'ÁO SƠ MI HỌA TIẾT XÁM SM058');
INSERT INTO THONGTINSP
VALUES ('SP29', N'ÁO SƠ MI NAZAFU TAY NGẮN 053', 'OWEN', N'ÁO SƠ MI NAZAFU TAY NGẮN 053');
INSERT INTO THONGTINSP
VALUES ('SP30', N'ÁO SƠ MI NAZAFU HỌA TIẾT CHÌM 050', 'OWEN', N'ÁO SƠ MI NAZAFU HỌA TIẾT CHÌM 050');
INSERT INTO THONGTINSP
VALUES ('SP31', N'ÁO SƠ MI NAZAFU HỌA TIẾT CHÌM 048', 'C LOCAL', N'ÁO SƠ MI NAZAFU HỌA TIẾT CHÌM 048');
INSERT INTO THONGTINSP
VALUES ('SP32', N'ÁO SƠ MI TRƠN NGẮN TAY ASM071', 'C LOCAL', N'ÁO SƠ MI TRƠN NGẮN TAY ASM071');
INSERT INTO THONGTINSP
VALUES ('SP33', N'ÁO SƠ MI LINEN CÓ TÚI SM069', 'C LOCAL', N'ÁO SƠ MI LINEN CÓ TÚI SM069');
INSERT INTO THONGTINSP
VALUES ('SP34', N'ÁO SƠ MI SỌC MÀU XANH ĐEN ASM067', 'C LOCAL', N'ÁO SƠ MI SỌC MÀU XANH ĐEN ASM067');
INSERT INTO THONGTINSP
VALUES ('SP35', N'ÁO SƠ MI HOA ASM066', 'C LOCAL', N'ÁO SƠ MI HOA ASM066');
INSERT INTO THONGTINSP
VALUES ('SP36', N'ÁO SƠ MI LOANG ASM065', 'C LOCAL', N'ÁO SƠ MI LOANG ASM065');
INSERT INTO THONGTINSP
VALUES ('SP37', N'ÁO SƠ MI HOA XÁM ASM064', 'C LOCAL', N'ÁO SƠ MI HOA XÁM ASM064');
INSERT INTO THONGTINSP
VALUES ('SP38', N'ÁO SƠ MI HỌA TIẾT BÔNG ASM063', 'C LOCAL', N'ÁO SƠ MI HỌA TIẾT BÔNG ASM063');
INSERT INTO THONGTINSP
VALUES ('SP39', N'ASM HỌA TIẾT LÁ CÂY MÀU ĐEN ASM062', 'C LOCAL', N'ASM HỌA TIẾT LÁ CÂY MÀU ĐEN ASM062');
INSERT INTO THONGTINSP
VALUES ('SP40', N'ÁO SƠ MI TRƠN VẢI CHÉO MÀU TRẮNG SM061', 'C LOCAL', N'ÁO SƠ MI TRƠN VẢI CHÉO MÀU TRẮNG SM061');
INSERT INTO THONGTINSP
VALUES ('SP41', N'ÁO SƠ MI TRƠN CÓ TÚI MÀU XANH SM057', 'JUNO', N'ÁO SƠ MI TRƠN CÓ TÚI MÀU XANH SM057');
INSERT INTO THONGTINSP
VALUES ('SP42', N'ÁO SƠ MI HỌA TIẾT ASM060', 'JUNO', N'ÁO SƠ MI HỌA TIẾT ASM060');
INSERT INTO THONGTINSP
VALUES ('SP43', N'ÁO SƠ MI HỌA TIẾT ASM059', 'JUNO', N'ÁO SƠ MI HỌA TIẾT ASM059');
INSERT INTO THONGTINSP
VALUES ('SP44', N'ÁO SƠ MI TRƠN ASM046', 'JUNO', N'ÁO SƠ MI TRƠN ASM046');
INSERT INTO THONGTINSP
VALUES ('SP45', N'ÁO SƠ MI CARO ASM040', 'JUNO', N'ÁO SƠ MI CARO ASM040');
INSERT INTO THONGTINSP
VALUES ('SP46', N'ÁO SƠ MI CARO ASM044', 'JUNO', N'ÁO SƠ MI CARO ASM044');
INSERT INTO THONGTINSP
VALUES ('SP47', N'ÁO SƠ MI MODAL ĐỐM ASM045', 'JUNO', N'ÁO SƠ MI MODAL ĐỐM ASM045');
INSERT INTO THONGTINSP
VALUES ('SP48', N'ÁO SƠ MI SỌC ASM039', 'JUNO', N'ÁO SƠ MI SỌC ASM039');
INSERT INTO THONGTINSP
VALUES ('SP49', N'ÁO SƠ MI CARO ASM043 MÀU NÂU', 'JUNO', N'ÁO SƠ MI CARO ASM043 MÀU NÂU');
INSERT INTO THONGTINSP
VALUES ('SP50', N'ÁO SƠ MI CARO ASM042 MÀU XÁM', 'JUNO', N'ÁO SƠ MI CARO ASM042 MÀU XÁM');

INSERT INTO PRODUCT
VALUES (1, N'ÁO THUN 1 LỖ', 'AO XAU QUA TROI', 1000, 1100, 1, 'img/products/1.jpg', 'img/products/2.jpg');
INSERT INTO PRODUCT
VALUES (2, N'ÁO THUN 2 LỖ', 'AO XAU QUA TROI', 2000, 2100, 2, 'img/products/3.jpg', 'img/products/4.jpg');
INSERT INTO PRODUCT
VALUES (3, N'ÁO THUN 3 LỖ', 'AO XAU QUA TROI', 3000, 3100, 3, 'img/products/5.jpg', 'img/products/6.jpg');
INSERT INTO PRODUCT
VALUES (4, N'ÁO THUN 4 LỖ', 'AO XAU QUA TROI', 4000, 4100, 4, 'img/products/7.jpg', 'img/products/8.jpg');
INSERT INTO PRODUCT
VALUES (5, N'ÁO THUN 5 LỖ', 'AO XAU QUA TROI', 5000, 5100, 5, 'img/products/9.jpg', 'img/products/10.jpg');
INSERT INTO PRODUCT
VALUES (6, N'ÁO THUN 6 LỖ', 'AO XAU QUA TROI', 6000, 6100, 6, 'img/products/11.jpg', 'img/products/12.jpg');
INSERT INTO PRODUCT
VALUES (7, N'ÁO THUN 7 LỖ', 'AO XAU QUA TROI', 7000, 7100, 7, 'img/products/13.jpg', 'img/products/14.jpg');
INSERT INTO PRODUCT
VALUES (8, N'ÁO THUN 8 LỖ', 'AO XAU QUA TROI', 8000, 8100, 8, 'img/products/15.jpg', 'img/products/1.jpg');
INSERT INTO PRODUCT
VALUES (9, N'ÁO THUN 9 LỖ', 'AO XAU QUA TROI', 9000, 9100, 9, 'img/products/1.jpg', 'img/products/2.jpg');


DELETE
TABLE FROM PRODUCT WHERE ID = 1;