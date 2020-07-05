insert into category(title) values ("Education");
insert into category(title) values ("Novel");
insert into category(title) values ("Literature");
insert into category(title) values ("Science");
insert into category(title) values ("History");
 
insert into subcategory(category_id, title) values (1, "High School");
insert into subcategory(category_id, title) values (1, "Self Development");
insert into subcategory(category_id, title) values (2, "Horror");
insert into subcategory(category_id, title) values (2, "Adventure"); -- cat ıd 5
insert into subcategory(category_id, title) values (3, "English Literary");
insert into subcategory(category_id, title) values (3, "Russian Literary");
insert into subcategory(category_id, title) values (4, "Engineering");
insert into subcategory(category_id, title) values (4, "Astronomy");
insert into subcategory(category_id, title) values (4, "Biology");
insert into subcategory(category_id, title) values (5, "World History");
insert into subcategory(category_id, title) values (5, "Ottoman history");
 


insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('H.P. Lovecraft',5, 1928, 'The Call of Cthulhu', 1291239123, 'https://i.dr.com.tr/cache/136x136-0/originals/0001796506001-1.jpg','Atlas', 'Fascinating story !!', 2,21);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dan Brown',5, 2001, 'Lost Symbol', 1291264743, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000317832-1.jpg','Gold', 'Amazing story !!', 5,35.00);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dan Brown',5, 2003, 'Angels and Deamons', 1231239123, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000151552-1.jpg','Gold', 'Interesting story !!', 10,24.00);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Stephan King',4, 1998, 'It', 9871264743, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000706011-1.jpg','Gold', 'Horrible story !!', 3,19);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Alein Kentigerna',4, 2015, 'Hallucinations', 2231239123, 'https://i.dr.com.tr/cache/154x170-0/originals/0000000434749-1.jpg','Panama', 'Good story !!', 7,24.00);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Kolektif',2, 2019, 'Geometri 0', 1231232323, 'https://i.dr.com.tr/cache/154x170-0/originals/0001779881001-1.jpg','Karekok', 'Learn quickly !!', 11,30.00);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Orhan Kutay',2, 2019, 'TYT Fizik Deneme', 1231239223, 'https://i.dr.com.tr/cache/154x170-0/originals/0001863577001-1.jpg','Delta', 'To Win !!', 6,30.00);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Eckhart Tolle',3, 2018, 'The Power of Now', 1232339123, 'https://i.dr.com.tr/cache/136x136-0/originals/0001818159001-1.jpg','Hodder', 'Improve yourself !!', 9,30.00);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Sarah Harvey',3, 2019, 'Kaizen', 1231239123, 'https://i.dr.com.tr/cache/600x600-0/originals/0001844661001-1.jpg','Bluebird', 'For japons !!', 8,10.00);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('George Orwell',6, 1990, '1984', 1291966773, 'https://i.dr.com.tr/cache/136x136-0/originals/0000000064038-1.jpg','Can', ' Distopik', 1,20.00);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('George Orwell',6, 1985, 'Animal Farm', 12732900775, 'https://i.dr.com.tr/cache/136x136-0/originals/0000000105409-1.jpg','Can', 'Utopik', 17,30.00);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Tolstoy',7, 2006, 'What Men Live By', 124545456773, 'https://i.dr.com.tr/cache/136x136-0/originals/0001870178001-1.jpg','Platanus', ' masterpiece', 3,20.00);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dostoyevski',7, 2007, 'Crime and Punishment', 1289898900775, 'https://i.dr.com.tr/cache/136x136-0/originals/0001869884001-1.jpg','Platanus', 'masterpiece', 5,26.00);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Kolektif',8, 2009, 'Studies In Engineering', 124919256773, 'https://i.dr.com.tr/cache/136x136-0/originals/0001791602001-1.jpg','Night', 'information for studies', 25,20);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Kolektif',8, 2010, 'Computer Engineering', 12898810063, 'https://i.dr.com.tr/cache/136x136-0/originals/0001694291001-1.jpg','DaisyScience', 'easy to learn', 71,21);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Eric Simon',10, 2000, 'Biology The Core', 66666656773, 'https://i.dr.com.tr/cache/136x136-0/originals/0000000639975-1.jpg','Night', 'information about cell', 2,250);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Abidin Temizer',11, 1999, 'Glimpses Of Balkans', 12495555444, 'https://i.dr.com.tr/cache/136x136-0/originals/0001700399001-1.jpg','Night', 'balkans culturucal history', 6,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Dorling Kinder',11, 1987, 'Who Chanched History ', 2255337788, 'https://i.dr.com.tr/cache/136x136-0/originals/0001837504001-1.jpg','DK Publish', 'amazing scientists', 19,26);

insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Ilber Ortayli',12, 1950, 'Ottoman Studies', 9988776655, 'https://i.dr.com.tr/cache/136x136-0/originals/0001807565001-1.jpg','Kronik Kitap', 'ottomon story', 4,23);
insert into book(author_name,subcategory_id,publish_year,title,isbn,image_url,publisher_name,subjects,count,price) value ('Halil Inancik',12, 1955, 'The Ottoman Empire', 7744990033, 'https://i.dr.com.tr/cache/136x136-0/originals/0001741995001-1.jpg','Kronik Kitap', 'ottoman story', 3,26);


-- INSERTS FOR DEL5 --

insert into customer(customer_name,phone,email,customer_password,address) value ('Aybar Tas', '0544 537 36 34', 'aybar@xyz.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Cok Evler Sitesi Eryaman/Ankara');
insert into customer(customer_name,phone,email,customer_password,address) value ('Oktay Ugurlu', '0544 537 36 34', 'oktayugurlu@xyz.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Yok Evler Sitesi Eryaman/Ankara');
insert into customer(customer_name,phone,email,customer_password,address) value ('Yusuf Tas', '0544 537 36 34', 'yusuf@xyz.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Cok Evler Sitesi Eryaman/Ankara');
insert into customer(customer_name,phone,email,customer_password,address) value ('Koray Tas', '0544 537 36 34', 'koray@xyz.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Hep Evler Sitesi Eryaman/Ankara');
insert into customer(customer_name,phone,email,customer_password,address) value ('Deniz Tas', '0544 537 36 34', 'deniz@xyz.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Guzel Evler Sitesi Eryaman/Ankara');

insert into page_admin(admin_password,email) value ('3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'admin@admin');

insert into mail(page_admin_id,customer_id,title,content) value (1, 1,'hello','hello dear');

insert into book_comment(book_id,customer_id,rate,user_comment) value (2, 1,4,'this book is sucks but i like it'); 
insert into book_comment(book_id,customer_id,rate,user_comment) value (4, 2,5,'I liked but dont buy this book :( Why Dan Brown wrote this book! If I were Dan Brown, I had wrote better !!!');
insert into book_comment(book_id,customer_id,rate,user_comment) value (6, 1,4,'Average book');
insert into book_comment(book_id,customer_id,rate,user_comment) value (8, 2,5,'I liked this book');
insert into book_comment(book_id,customer_id,rate,user_comment) value (9, 2,4,'Average book');
insert into book_comment(book_id,customer_id,rate,user_comment) value (3, 1,5,'Not bad, not bad...');
insert into book_comment(book_id,customer_id,rate,user_comment) value (10, 1,5,'Not bad, not bad...');
insert into book_comment(book_id,customer_id,rate,user_comment) value (10, 2,4,'Not bad, not bad...');

insert into campaign(book_id,discount_percentage,note,image_url) value (1, 30,"Horror Books 50% OFF !!","https://i.pinimg.com/originals/5c/10/30/5c103001e28430a2f7f18bc3f4d2dde5.jpg");
insert into campaign(book_id,discount_percentage,note,image_url) value (3, 50,"Read this books with %20 OFF!!","https://yazname.com/wp-content/uploads/2016/11/Wonderful-Book-Wallpaper.jpg"); 
insert into campaign(book_id,discount_percentage,note,image_url) value (4, 50,"Extreme campaign on this books!","https://wallpaperaccess.com/full/124378.jpg"); 

insert into Courier_Company(url,phone,company_name,price) value ("https://www.ups.com.tr/","03122835298","UPS",15.00);
insert into Courier_Company(url,phone,company_name,price) value ("https://www.mng.com.tr/tr/express.html","08502734563","MNG Cargo",15.00);
insert into Courier_Company(url,phone,company_name,price) value ("https://www.aras.com.tr/tr/express.html","08502734563","Aras Cargo",15.00);
insert into Courier_Company(url,phone,company_name,price) value ("https://www.surat.com.tr/tr/express.html","08502734563","Surat Cargo",15.00);

insert into Purchase_Request(is_confirmed,courier_company_id) value (0,1); 
insert into Purchase_Request(is_confirmed,courier_company_id) value (0,1); 
insert into Purchase_Request(is_confirmed,courier_company_id) value (1,1); 
insert into Purchase_Request(is_confirmed,courier_company_id) value (1,1); 

insert into cart(book_id,customer_id,count, purchase_request_id) value (12,1, 3, 1); 
insert into cart(book_id,customer_id,count, purchase_request_id) value (13,1, 1, 1);
insert into cart(book_id,customer_id,count, purchase_request_id) value (12,2, 2, 2);
insert into cart(book_id,customer_id,count, purchase_request_id) value (13,2, 3, 2);
 
insert into cart(book_id,customer_id,count, purchase_request_id) value (2, 1, 1, 3); 
insert into cart(book_id,customer_id,count, purchase_request_id) value (4, 2, 2, 4);
insert into cart(book_id,customer_id,count, purchase_request_id) value (6, 1, 1, 3); 
insert into cart(book_id,customer_id,count, purchase_request_id) value (8, 2, 2, 4);
insert into cart(book_id,customer_id,count, purchase_request_id) value (9, 2, 1, 4);
insert into cart(book_id,customer_id,count, purchase_request_id) value (3, 1, 2, 3);
insert into cart(book_id,customer_id,count, purchase_request_id) value (5, 1, 1, 3);
insert into cart(book_id,customer_id,count, purchase_request_id) value (3, 2, 3, 4);
insert into cart(book_id,customer_id,count, purchase_request_id) value (10, 1, 1, 3);
insert into cart(book_id,customer_id,count, purchase_request_id) value (10, 2, 2, 4);

insert into In_Cargo(purchase_request_id,export_date,arrival_date) value (3, '2020-8-11 13:23:44','2020-8-8 13:23:44'); 
insert into In_Cargo(purchase_request_id,export_date,arrival_date) value (4, '2020-3-11 13:23:44','2020-3-8 13:23:44'); 


insert into payment_service(payment_name,account_number) value ('Garanti Bank', '01234–12345678'); 
insert into payment_service(payment_name,account_number) value ('Fiba Bank',  '01234–98352728'); 
insert into payment_service(payment_name,account_number) value ('Deniz Bank',   '23465–98352728'); 
insert into payment_service(payment_name,account_number) value ('Oktay Bank',   '23465–93423728'); 
insert into payment_service(payment_name,account_number) value ('TEB Bank',   '23465–93423728'); 
 