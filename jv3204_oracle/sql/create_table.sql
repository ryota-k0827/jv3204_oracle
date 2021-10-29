DROP TABLE user_info PURGE;
DROP TABLE products PURGE;
DROP TABLE categories PURGE;

CREATE TABLE user_info (
    id CHAR(6) CONSTRAINT usr_id_pk PRIMARY KEY,
    name VARCHAR2(26) CONSTRAINT usr_name_nn NOT NULL,
    pass VARCHAR2(10) CONSTRAINT usr_pwd_nn NOT NULL,
    classification CHAR(1) CONSTRAINT usr_cl_nn NOT NULL,
    create_date TIMESTAMP(0) DEFAULT SYSDATE NOT NULL
);

INSERT ALL
INTO user_info (id, name, pass, classification) VALUES (11111, 'member', '111111', 1)
INTO user_info (id, name, pass, classification) VALUES (22222, 'admin', '222222', 2)
INTO user_info (id, name, pass, classification) VALUES (33333, '山田忠明', '333333', 2)
INTO user_info (id, name, pass, classification) VALUES (44444, '齊藤新三', '444444', 2)
SELECT * FROM DUAL;

CREATE TABLE categories (
    category_id CHAR(2)
        CONSTRAINT cg_cid_pk PRIMARY KEY,
    category_name VARCHAR2(20)
        CONSTRAINT cg_cid_nn NOT NULL
);

INSERT ALL
INTO categories (category_id, category_name) VALUES (1, '果物')
INTO categories (category_id, category_name) VALUES (2, '野菜')
INTO categories (category_id, category_name) VALUES (3, '肉')
INTO categories (category_id, category_name) VALUES (4, '飲料')
SELECT * FROM DUAL;

CREATE TABLE products (
    no NUMBER(3)
        CONSTRAINT pd_no_pk PRIMARY KEY,
    product_name VARCHAR2(26) CONSTRAINT pd_name_nn NOT NULL,
    category_id CHAR(2)
        CONSTRAINT pd_cid_fk REFERENCES categories(category_id)
        CONSTRAINT pd_cid_nn NOT NULL,
    price NUMBER(11) CONSTRAINT pd_price_nn NOT NULL
);

INSERT ALL
INTO products(no, product_name, category_id, price) VALUES (1,'メロン', 1, 600)
INTO products(no, product_name, category_id, price) VALUES (2,'スイカ', 1, 900)
INTO products(no, product_name, category_id, price) VALUES (3,'小松菜', 2, 100)
INTO products(no, product_name, category_id, price) VALUES (4,'小松菜奈', 2, 100000000)
INTO products(no, product_name, category_id, price) VALUES (5,'コーヒー', 4, 100)
INTO products(no, product_name, category_id, price) VALUES (6,'ベーコン', 3, 200)
SELECT * FROM DUAL;

COMMIT;