# INSERT statuses VALUES (1, 'NOT_CONFIRMED');
# INSERT statuses VALUES (2, 'CONFIRMED');
# INSERT statuses VALUES (3, 'DELETED');

# INSERT INTO `custom_users` (`email`, `login_name`, `password_hash`, `salt`, `image_url`, `role_type`, `status`) VALUES ('safronova9821@gmail.com', 'sansdfs3d', '33C296FF1967DFB83B8B09F04F684064E5FD8AAC58B9B780A091D475FDFEAF93', '041C0C311FEEED505E709215589B9302', '/static/images/bakery1.jpg', 'USER', 'CONFIRMATION_AWAITING');
# DELETE FROM `custom_users` WHERE `email`='hjdbc@email.com';
# DELETE FROM `custom_users` WHERE `email`='fjjd@gmail.com';
# DELETE FROM `custom_users` WHERE `email`='dshbd@email.com';
# DELETE FROM `custom_users` WHERE `email`='anna.safrr@email.com';
# DELETE FROM `custom_users` WHERE `email`='asnna@email.com';
# DELETE FROM `custom_users` WHERE `email`='safr@email.com';
# DELETE FROM `custom_users` WHERE `email`='safronova9821@gmail.com';
# INSERT INTO `custom_users`(`email`, `login_name`, `password_hash`, `salt`, `image_url`, `role_type`, `status`)VALUES ('fuck.safrr@gmail.com', 'sadsaddsa5da', 'C6DAC1363819A533931F03103E99CD9CB2F94849E5C02A824D0D3166AC34D9D0', '2927892F81E927776F1455523B6134B1', '/static/images/bakery1.jpg', 'USER', 'NORMAL');

# private static final String FIND_BASKET_BY_USER =
#             "SELECT b_id, b_total_cost, b_user_id " +
#                     "FROM baskets" +
#                     "JOIN users ON baskets.b_user_id = users.u_id" +

# INSERT INTO users (u_login, u_password, u_email, u_role, u_name, u_surname, u_address, u_phone) VALUES ('annaSafr2','$2a$12$zsNPw17.WXM13Mjq.s/zLOzqYq8EfR30p.XWNiB0UrbW3FDYLxSzS','safronova9821@gmail.com','USER','Anna','Safronova','Zahjdfhjf 3','+264783748762')
# DELETE FROM `users` WHERE `u_email`='safronova9821@gmail.com';

# SELECT u_id, u_email, u_login, u_password, u_role, u_name, u_surname, u_address, u_phone FROM users WHERE u_email = 'safronova9821@gmail.com'
# DELETE FROM baskets WHERE b_user_id ='2';

#  UPDATE users SET u_role = 'USER' WHERE  u_id = '2';
# INSERT INTO dessert_types (dt_id, dt_category, dt_description) VALUES ('1', 'PASTRIES','Pastries');
# INSERT INTO dessert_types (dt_id, dt_category, dt_description) VALUES ('2', 'BAKERY','Bakery');
# INSERT INTO dessert_types (dt_id, dt_category, dt_description) VALUES ('3', 'CHOCOLATE','Chocolate');

# INSERT INTO desserts (d_name, d_description, d_price, d_weight, d_dessert_type_id) VALUES ('Cheesecake','Bourbon vanilla cheese cream, whole grain shortbread base with oatmeal, fresh berries.',10.00,175,1)
# DELETE FROM desserts WHERE d_id ='6';
# INSERT INTO desserts (d_name, d_description, d_price, d_weight, d_dessert_type_id)VALUES ('Cheesecake','Bourbon vanilla cheese cream, whole grain shortbread base with oatmeal, fresh berries.',10.00,1,175)
#  INSERT INTO storages (st_count, st_dessert_id)VALUES (3,5)
# DELETE FROM storages WHERE st_id ='1';
# SELECT d_id, d_name, d_description, d_price, d_weight, d_image, dt_id, dt_category, dt_description, st_count FROM desserts JOIN dessert_types ON desserts.d_dessert_type_id = dessert_types.dt_id JOIN storages ON desserts.d_id = storages.st_dessert_id WHERE (d_id = 7)
# UPDATE desserts SET name = 'Cheesecake', description = 'Bourbon vanilla cheese cream, whole grain shortbread base with oatmeal, fresh berries.', price = 10.00, d_weight = 175, d_dessert_type_id = 1WHERE d_id =7
# DELETE FROM desserts WHERE d_id = 7;
