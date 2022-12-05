CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) not NULL,
  `email` varchar(255) not NULL
);

CREATE TABLE `content` (
  `id` int PRIMARY KEY not NULL AUTO_INCREMENT,
  `title` varchar(255) not null,
  `body` varchar(225) not null,
  `created_at` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int,
  FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
);

CREATE TABLE `category` (
  `id` int PRIMARY KEY not null AUTO_INCREMENT,
  `name` varchar(255) not null
);

CREATE TABLE `content_category` (
  `id` int PRIMARY KEY not null AUTO_INCREMENT,
  `contentId` int not null,
  FOREIGN KEY (`contentID`) REFERENCES `content`(`id`),
  `categoryId` int not null,
  FOREIGN KEY (`categoryId`) REFERENCES `category`(`id`)
);

CREATE TABLE `role` (
  `id` int PRIMARY KEY not null AUTO_INCREMENT,
  `name` varchar(255) not null
);

ALTER TABLE `user` ADD `roleId` int;
ALTER TABLE `user` ADD FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);