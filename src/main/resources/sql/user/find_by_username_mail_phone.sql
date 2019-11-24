select u.id userId, username, password, first_name firstName, mid_name midName, last_name lastName, is_active isActive, avatar_url avatarUrl, birthday, address, email, phone_number phoneNumber, gender_value gender, role_name roleName
from users u
join person p on u.person_id = p.id
join gender g on g.id = p.gender_id
join user_role ur on u.id = ur.user_id
join role r on r.id = ur.role_id
where 1 =1

