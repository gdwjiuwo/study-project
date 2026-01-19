import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError =()=>ElMessage.error('请求失败')
const defaultFailure =(message)=>ElMessage.warning( message)
function post(url,data,success,failure=defaultFailure,error=defaultError){
    axios.post(url,data,{
        headers:{
            "Content-Type":"application/x-www-form-urlencoded"
        },
        withCredentials:true
    }).then(({data})=>{
        if(data.success)
            success(data.message,data.status)
        else
            failure(data.message,data.status)
    }).catch(err => error(err))
}

function get(url,success,failure=defaultFailure,error=defaultError){
    axios.post(url,{
        withCredentials:true
    }).then(({data})=>{
        if(data.success)
            success(data.message,data.status)
        else
            failure(data.message,data.status)
    }).catch(err => error(err))
}
export {
    post,
    get
}