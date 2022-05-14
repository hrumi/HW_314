function getRoleList(user) {
    let roleList = document.createElement('ul');
    for (let i = 0; i < user.roles.length; i++) {
        let role = document.createElement('li');
        role.textContent = user.roles[i].role + " ";
        roleList.appendChild(role);
    }
    return roleList;
}