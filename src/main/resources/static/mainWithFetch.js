//список функций в объект
 const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=UTF-8',
        'Referer': null
    },
    getAllUsers: async () => await fetch('/api/users'),
    addUser: async (user) => await fetch('/api/users', {method: "POST", headers: userFetch.head, body: JSON.stringify(user)}),
    updateUser: async (user) => await fetch(`/api/users/`, {method: 'PUT', headers: userFetch.head, body: JSON.stringify(user)}),
    deleteUserByID: async (id) => await fetch(`/api/users/` + id, {method: 'DELETE', headers: userFetch.head}),
    aboutCurrentUser: async () => await fetch(`/api/currentUser`),
    getUserById: async (id) => await fetch(`/api/users/` + id),
}


getUsers()
infoUser()

//получаем юзеров - заполняем таблицу
function getUsers() {
    userFetch.getAllUsers()
    .then(res => { res.json()
    .then(users => {
        users.forEach((user) => {
            console.log(user)
            let userRoles = getRoles(user.roles)
            let insert =
                `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${userRoles}</td>
                <td><button type="submit" onclick="editUser(${user.id})" class="btn btn-info" data-toggle="modal" data-target="#modal-container-editUser">Edit</button></td>
                <td><button type="submit" onclick="deleteUser(${user.id})" class="btn btn-danger" data-toggle="modal" data-target="#modal-container-deleteUser">Delete</button></td>
                </tr>`
            document.querySelector('#bodyWithUsers').insertAdjacentHTML('beforeend', insert)
        })
    })
    })
}

//listener для кнопки добавления нового user
const buttonNewUser = document.querySelector('#buttonNewUser')
buttonNewUser.addEventListener('click', addNewUser, false)
//добавление нового user
function addNewUser(env) {
    env.preventDefault()
    let name = document.getElementById('newUserName').value
    let surname = document.getElementById('newUserSurname').value
    let age = document.getElementById('newUserAge').value
    let email = document.getElementById('newUserEmail').value
    let password = document.getElementById('newUserPassword').value
    //получаем роли
    let elementWithRoles = document.getElementById('newUserRoles')
    let role = elementWithRoles.options[elementWithRoles.selectedIndex].value
    let roles = addRoles(role)
    let user = {
        name: name,
        surname: surname,
        age: age,
        email: email,
        password: password,
        roles: roles
    };
    userFetch.addUser(user).then(() => {
        //чистим
        document.getElementById('newUserName').value = ``;
        document.getElementById('newUserSurname').value = ``;
        document.getElementById('newUserAge').value = ``;
        document.getElementById('newUserEmail').value = ``;
        document.getElementById('newUserPassword').value = ``;
        document.getElementById('newUserRoles').value = ``;
        //чистим список юзеров, так как добавленный не появился
        document.getElementById('bodyWithUsers').innerHTML = ``;
        getUsers();
    })
}

//заполняем модальное окно данными user для edit
function editUser(id) {
    userFetch.getUserById(id)
        .then(res => {
            res.json().then(user => {
                $('#idEdit').val(user.id)
                $('#nameEdit').val(user.name)
                $('#surnameEdit').val(user.surname)
                $('#ageEdit').val(user.age)
                $('#emailEdit').val(user.email)
                $('#passwordEdit').val("")
                $('#roleEdit').val(getRoles(user.roles))
            })
        })
}

//обновляем user, вызывается на кнопке в html
function updateUser() {
    let id = document.getElementById('idEdit').value;
    let name = document.getElementById('nameEdit').value;
    let surname = document.getElementById('surnameEdit').value;
    let age = document.getElementById('ageEdit').value;
    let email = document.getElementById('emailEdit').value;
    let password = document.getElementById('passwordEdit').value;
    //получаем роли
    let elementWithRoles = document.getElementById('roleEdit')
    let role = elementWithRoles.options[elementWithRoles.selectedIndex].value
    let roles = addRoles(role)

    let user = {
        id: id,
        name: name,
        surname: surname,
        age: age,
        email: email,
        password: password,
        roles: roles
    };

    userFetch.updateUser(user).then(() => {
        document.getElementById('idEdit').value = ``;
        document.getElementById('nameEdit').value = ``;
        document.getElementById('surnameEdit').value = ``;
        document.getElementById('ageEdit').value = ``;
        document.getElementById('emailEdit').value = ``;
        document.getElementById('passwordEdit').value = ``;
        document.getElementById('roleEdit').value = ``;
        //чистим таблицу user, так как обновленного не видно
        document.getElementById('bodyWithUsers').innerHTML = ``;
        getUsers();
        $('#modal-container-editUser').modal('hide');

    })
}

//заполняем модальное окно данными user для delete
function deleteUser(id) {
    userFetch.getUserById(id)
        .then(res => {
            res.json().then(user => {
                $('#idDelete').val(user.id)
                $('#nameDelete').val(user.name)
                $('#surnameDelete').val(user.surname)
                $('#ageDelete').val(user.age)
                $('#emailDelete').val(user.email)
                $('#roleDelete').val(getRoles(user.roles))
            })
        })
}

//удаляем user, вызывается на кнопке в html
function deleteUserById() {
    let id = document.getElementById('idDelete').value;
    userFetch.deleteUserByID(id).then(() => {
        document.getElementById('idDelete').value = ``;
        document.getElementById('nameDelete').value = ``;
        document.getElementById('surnameDelete').value = ``;
        document.getElementById('ageDelete').value = ``;
        document.getElementById('emailDelete').value = ``;
        document.getElementById('roleDelete').value = ``;
        //чистим список юзеров, так как удаленный не уходит
        document.getElementById('bodyWithUsers').innerHTML = ``;
        getUsers();
        $('#modal-container-deleteUser').modal('hide');
    });
}

//роли user в строку
function getRoles(roles) {
    let result = ''
    for (let role of roles) {
        result += role.name.substring(5)
        result += ' '
    }
    result = result.substring(0, result.length - 1)
    return result
}

//роли нового user
function addRoles(role) {
    let roles = [];
    roles.push({id: 1, name: 'ROLE_USER', authority: 'ROLE_USER'});
    if (role === 'ADMIN') {
        roles.push({id: 2, name: 'ROLE_ADMIN', authority: 'ROLE_ADMIN'});
    }
    return roles;
}

function infoUser() {
    userFetch.aboutCurrentUser()
        .then(res => res.json())
        .then(user => {
            let roles = getRoles(user.roles);
            document.querySelector('#currentUserData').innerHTML = `${user.email} with roles: ${roles}`;
            document.querySelector('#userInfoTable').innerHTML = `
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
            </tr>
            `;
        });
}


