export const service = {
    checkUser,
    empInfo
}

function checkUser(username, password){
    console.log("user:"+username+" password:"+password);
    const requestOption = {
        method: 'GET'
    }
    return fetch('http://localhost:8080/HrPortal/checkUser?empId='+username+'&password='+password, requestOption)
    .then(res => {
        console.log(res+" "+username);
        return res.text();
    })
}


function empInfo(empNo) {
        const requestOption = {
            method: 'GET',
            headers: { "Content-Type": "application/json" }
        }
        return fetch('http://localhost:8080/HrPortal/getEmployeeHomePage?empNo='+empNo, requestOption).then(res => {
            // console.log(res);
            return res;
        })
    }