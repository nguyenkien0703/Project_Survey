<template>
    <div class="wrapper">
        <PageHearder />
        <div class="d-flex align-items-center justify-content-center " style="height: 60vh;">
            <div class="bg-rg">
                <form @submit.prevent="registerUser">
                    <div class="form-outline form-white mb-4">
                        <input type="text" id="typeUsername" v-model="userDto.userName" class="form-control form-control-lg"
                            placeholder="Nhập username" />
                    </div>
                    <div class="form-outline form-white mb-4">
                        <input type="password" id="typePasswordX" v-model="userDto.passwordAccount"
                            class="form-control form-control-lg" placeholder="Nhập mật khẩu" />
                    </div>
                    <div class="form-outline form-white mb-4">
                        <input type="password" id="typePasswordX" v-model="userDto.checkPass"
                            class="form-control form-control-lg" placeholder="Nhập lại mật khẩu" />
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <div class="mb-3">
                                <select class="form-select form-select-lg" v-model="userDto.departmentId" name="" id="">
                                    <option value="">-- Vị trí --</option>
                                    <option v-for="department in departments" :key="department.id" :value="department.id">{{
                                        department.nameDepartment }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="mb-3">
                                <select class="form-select form-select-lg" v-model="userDto.roleId" name="" id="">
                                    <option value="">-- Vai Trò --</option>
                                    <option v-for="role in roles" :key="role.id" :value="role.id">{{
                                        role.nameRole }}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex align-items-center justify-content-center">
                        <button type="submit" class="btn btn-success">Đăng kí</button>
                    </div>

                    <div class="d-flex align-items-center justify-content-center mt-2">
                        <router-link to="/login">Quay lại đăng nhập</router-link>
                    </div>
                </form>
            </div>
        </div>
        <PageFooter />
    </div>
</template>

<script>
import axios from 'axios';
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
export default {
    name: 'PageRegisterUser',
    components: {
        PageHearder, PageFooter
    },

    data: () => ({
        departments: [],
        roles: [],
        userDto: {
            userName: '',
            passwordAccount: '',
            checkPass: '',
            departmentId: '',
            roleId: ''
        },
    }),
    mounted() {
        axios.get('http://localhost:8081/employee/roles-department')
            .then(response => {
                this.departments = response.data.departments;
                this.roles = response.data.roles; // Gán dữ liệu từ API vào mảng departments
            })
            .catch(error => {
                console.error('Lỗi khi gọi API departments:', error);
            });

    },
    methods: {
        onSubmit() {
            alert('Thêm Thành công!');
        }
        ,
        registerUser() {
            const newUser = {
                userName: this.userDto.userName,
                departmentId: this.userDto.departmentId,
                roleId: this.userDto.roleId,
                passwordAccount: this.userDto.passwordAccount,
                checkPass: this.userDto.checkPass,
            };
            console.log(newUser);
            axios.post('http://localhost:8081/auth/register', newUser)
                .then(() => {
                    alert('User added successfully!');
                    // Reset the form
                    this.userDto.userName = '';
                    this.userDto.passwordAccount = '';
                    this.userDto.checkPass = '';
                    this.userDto.departmentId = '';
                    this.userDto.roleId = '';
                    this.$router.push({ name: 'register-success' });
                })
                .catch((error) => {
                    alert('Failed to add user: ' + error.response.data.message);
                    this.$router.push({ name: 'register-user' });
                });


        },
    },
}
</script>



<style>
.bg-rg {
    padding: 30px;
    background-color: #BBD6D4;
    border-radius: 20px;
}
</style>