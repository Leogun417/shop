/**
 * Created by 傲然 on 2017/2/13.
 */
function registerValidate() {
    if (registerForm.username.value == "") {
        window.alert("账号不能为空！");
        return false;
    }
    if (registerForm.password.value == "") {
        window.alert("密码不能为空！");
        return false;
    }
    if (registerForm.nickname.value == "") {
        window.alert("昵称不能为空！");
        return false;
    }

    if (registerForm.password.value.length < 6) {
        window.alert("密码不能小于6位！");
        return false;
    }
}

function loginValidate() {
    if (loginForm.username.value == "") {
        window.alert("账号不能为空！");
        return false;
    }
    if (loginForm.password.value == "") {
        window.alert("密码不能为空！");
        return false;
    }
}
function updateValidate() {
    if (updateForm.password.value == "") {
        window.alert("密码不能为空！");
        return false;
    }

    if (updateForm.password.value.length < 6) {
        window.alert("密码不能小于6位");
        return false;
    }

    if (updateForm.nickname.value == "") {
        window.alert("昵称不能为空！");
        return false;
    }
}

function addAddressValidate() {
    if (addAddressForm.address.value == "") {
        window.alert("地址不能为空！");
        return false;
    }

    if (addAddressForm.phone.value == "") {
        window.alert("联系电话不能为空");
        return false;
    }

    if (isNaN(addAddressForm.phone.value) || addAddressForm.phone.value.length > 20) {
        window.alert("联系电话不合法");
        return false;
    }

    if (addAddressForm.postcode.value == "") {
        window.alert("邮编不能为空！");
        return false;
    }

    if (isNaN(addAddressForm.postcode.value) || addAddressForm.postcode.value.length != 6) {
        window.alert("邮编不合法");
        return false;
    }
}
