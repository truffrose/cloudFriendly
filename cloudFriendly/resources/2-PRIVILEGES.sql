CREATE USER 'cloudFriendly'@'localhost' IDENTIFIED BY 'CV8H4Y';

GRANT ALL PRIVILEGES ON `cloudFriendly_db`.* TO 'cloudFriendly'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;