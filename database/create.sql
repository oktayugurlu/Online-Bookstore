create schema if not exists BOOKSTORE; 
  
 
create table if not exists Category(
	id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50)
);
    
create table if not exists Subcategory(
	id int AUTO_INCREMENT PRIMARY KEY,
    category_id int default null, 
    title varchar(255) , 
    foreign key(category_id) references Category(id) on update cascade on delete cascade
);

create table if not exists Book(  
	id int AUTO_INCREMENT PRIMARY KEY, 
    subcategory_id integer default 1, 
	author_name varchar(255),
	publish_year int,
    title varchar(255),
    isbn varchar(20),
    image_url varchar(2048),
    publisher_name varchar(255),
    added_date DateTime DEFAULT CURRENT_TIMESTAMP,
    subjects varchar(600),
    count int,
    price float(10,2),
    price_with_campaign float(10,2),
    foreign key(subcategory_id) references Subcategory(id) on update cascade on delete set null
);
    
    
create table if not exists Campaign( 
	id int AUTO_INCREMENT PRIMARY KEY,
	book_id int not null,
    discount_percentage float,
    note varchar(600),
    image_url varchar(2048),
    foreign key (book_id) references Book(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table if not exists Customer(
	id int AUTO_INCREMENT PRIMARY KEY,
    customer_name varchar(255),
    phone varchar(255),
    email varchar(255),
    customer_password varchar(300),
    address varchar(255)
);
 create table if not exists Page_Admin(
	id int AUTO_INCREMENT PRIMARY KEY,
    admin_password varchar(300),
    email varchar(255)
 );
 
 create table if not exists Mail(
	id int AUTO_INCREMENT PRIMARY KEY,
	page_admin_id int,
    customer_id int,
    title varchar(255),
    content varchar(10000),
    send_date DateTime DEFAULT CURRENT_TIMESTAMP,
    foreign key (page_admin_id) references Page_Admin(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (customer_id) references Customer(id) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 
create table if not exists Book_Comment(
	id int AUTO_INCREMENT PRIMARY KEY,
	book_id int,
    customer_id int,
    rate float,
    user_comment varchar(600),
    added_date DateTime DEFAULT CURRENT_TIMESTAMP,
	foreign key (book_id) references Book(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (customer_id) references Book(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table if not exists Payment_Service(
	id int AUTO_INCREMENT PRIMARY KEY,
    payment_name varchar(255),
    account_number varchar(40)
);

create table if not exists Courier_Company(
	id int AUTO_INCREMENT PRIMARY KEY,
    url varchar(2048),
    phone varchar(15),
    company_name varchar(255),
    price float
);

create table if not exists billing_information(
	id int AUTO_INCREMENT PRIMARY KEY,
	customer_id int ,
    customer_name varchar(255),
    phone varchar(255),
    email varchar(255), 
    address varchar(255),
    foreign key (customer_id) references customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table if not exists Purchase_Request(
	id int AUTO_INCREMENT PRIMARY KEY,
    payment_service_id int,
    courier_company_id int, 
    billing_information_id int,
    purchase_date datetime DEFAULT CURRENT_TIMESTAMP,
    is_confirmed int,
    foreign key (payment_service_id) references Payment_Service(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (courier_company_id) references Courier_Company(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (billing_information_id) references billing_information(id) ON DELETE CASCADE ON UPDATE CASCADE
);


create table if not exists Cart(
	id int AUTO_INCREMENT PRIMARY KEY,
    book_id int,
    customer_id int,
    count int,
    purchase_request_id int,
    foreign key (book_id) references Book(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (customer_id) references Book(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (purchase_request_id) references purchase_request(id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table if not exists In_Cargo(
	id int AUTO_INCREMENT PRIMARY KEY,
    purchase_request_id int,
    export_date datetime,
    arrival_date datetime,
    foreign key (purchase_request_id) references Purchase_Request(id) ON DELETE CASCADE ON UPDATE CASCADE

);


DELIMITER $$ 
       -- DEFAULT ROW FOR Subcategory --
       
	insert into category(id,title) values (-1,'General'); 
	insert into subcategory(title,category_id) values ('General',-1);

	create trigger deleteSubcategoryTrigger after delete on subcategory
		for each row
	   begin
		   update book set subcategory_id=1 where subcategory_id is null ;
	   end$$ 
       
	create trigger deleteCategoryTrigger after delete on category
		for each row
	   begin
		   update Book set subcategory_id=1 where subcategory_id is null ;
	   end$$ 
	
    CREATE TRIGGER setpricewithcampaign BEFORE INSERT ON Book 
    FOR EACH ROW
		begin
			IF NEW.price_with_campaign IS NULL THEN
				SET NEW.price_with_campaign = NEW.price;
			END IF;
		end$$
        
	-- When below triggers are running, because of the book is updated, i create this trigger. Also it can be used for after update a a price
	CREATE TRIGGER updatepricewithcampaign BEFORE update ON Book 
    FOR EACH ROW
		begin
			IF NEW.price_with_campaign <> NEW.price THEN
				SET NEW.price_with_campaign = NEW.price - NEW.price * (SELECT distinct discount_percentage FROM book,campaign WHERE NEW.id = book_id)/100;
			END IF;
        end$$
        
	CREATE TRIGGER setpricewithcampaignaftercampaign after INSERT ON Campaign 
    FOR EACH ROW
		begin
			update Book as b set b.price_with_campaign = b.price - b.price * new.discount_percentage/100 where b.id =  new.book_id;
		end$$ 
	
	CREATE TRIGGER setpricewithcampaignafterupdatecampaign after UPDATE ON Campaign 
    FOR EACH ROW
		begin
			update Book set price_with_campaign = price - price * new.discount_percentage/100 where book.id =  new.book_id;
		end$$ 
        
	CREATE TRIGGER deletepricewithcampaignafterupdatecampaign after Delete ON Campaign 
    FOR EACH ROW
		begin
			update Book set price_with_campaign = price where book.id =  old.book_id;
		end$$ 
DELIMITER ;