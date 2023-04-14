<template>
    <div class="div">
        <div class="row ContentListSurvey">
            <div class="col-6 ">
                <div class="text-left">
                    <h2>
                        Cập nhật nhân viên
                    </h2>
                </div>
            </div>
        </div>
        <form class="d-flex" @submit.prevent="updateEmployee()">
            <div class="container bg-form mt-4">
                <div class="row bg-row">
                    <div class="col-7">
                        <div class="mb-3">
                            <label for="" class="form-label">Họ và tên :</label>
                            <input type="text" class="form-control  form-el" placeholder="" aria-describedby="helpId"
                                v-model="employee.userName" :class="{ 'is-invalid': invalidName }" required
                                @blur="validateName">
                            <div class="invalid-feedback">
                                Vui lòng nhập tên !!
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Số điện thoại:</label>
                            <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                                v-model="employee.phoneNumber" :class="{ 'is-invalid': invalidPhone }" required
                                @blur="validatePhone">
                            <div class="invalid-feedback">
                                Vui lòng nhập số điện thoại (10-11 số).
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Địa chỉ :</label>
                            <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                                v-model="employee.address" :class="{ 'is-invalid': invalidAddress }" required
                                @blur="validateAddress">
                            <div class="invalid-feedback">
                                Vui lòng nhập địa chỉ !!
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <div class="mb-3">
                                    <select class="form-select form-select-lg" v-model="employee.departmentId">
                                        <option value="">-- Vị trí --</option>
                                        <option v-for="department in departments" :key="department.id"
                                            :value="department.id" @blur="validateDepament">
                                            {{ department.nameDepartment }}</option>
                                    </select>
                                    <div v-if="invalidDepartment" class="invalid-feedback">
                                        Vui lòng chọn vị trí !!
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="mb-3 ">
                                    <select class="form-select form-select-lg" v-model="employee.roleId"
                                        @blur="validateRole">
                                        <option value="">-- Vai trò --</option>
                                        <option v-for="role in roles" :key="role.id" :value="role.id">{{
                                            role.nameRole }}</option>
                                    </select>
                                    <div v-if="invalidRole" class="invalid-feedback">
                                        Vui lòng chọn vai trò !!
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-5">
                        <div class="mb-3">
                            <label for="" class="form-label">Email :</label>
                            <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                                v-model="employee.email" :class="{ 'is-invalid': invalidEmail }" required
                                @blur="validateEmail">
                            <div class="invalid-feedback">
                                Vui lòng nhập Email và đúng định dạng !!
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Tài khoản :</label>
                            <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                                v-model="employee.nameAccount" :class="{ 'is-invalid': invalidAccount }" required
                                @blur="validateAccount">
                            <div class="invalid-feedback">
                                Vui lòng nhập tên tài khoản !!
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Mật khẩu :</label>
                            <input type="password" class="form-control form-el" placeholder="" aria-describedby="helpId"
                                v-model="employee.passwordAccount" :class="{ 'is-invalid': invalidPassword }" required
                                @blur="validatePassword">
                            <div class="invalid-feedback">
                                Vui lòng nhập mật khẩu !!
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="mb-3 ">
                                <select class="form-select form-select-lg" v-model="employee.sex">
                                    <option value="">-- Giới Tính --</option>
                                    <option value="1">Nam</option>
                                    <option value="0">Nữ</option>
                                </select>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-center align-items-center mt-3">
                    <button type="reset" class="btn  btn-danger me-5">Hủy</button>
                    <button type="submit" class="btn btn-success">Thêm</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'FormUpdateEmployee',
    data() {
        return {
            invalidName: false,
            invalidPhone: false,
            invalidAddress: false,
            invalidEmail: false,
            invalidAccount: false,
            invalidPassword: false,
            invalidRole: false,
            invalidDepartment: false,
            employee: {
                userName: '',
                email: '',
                nameAccount: '',
                phoneNumber: '',
                address: '',
                departmentId: '',
                roleId: '',
                passwordAccount: ''
            },
            departments: [],
            roles: []
        }
    },
    mounted() {
        // Gọi API "/departments" bằng Axios khi component được mounted
        axios.get(`http://localhost:8081/employee/getEmployee/${this.$route.params.id}`, {
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem('token')
            }
        })
            .then(response => {
                this.employee = response.data;
                console.log(this.employee);
            })
            .catch(error => {
                console.log(error);
            });
        axios.get('http://localhost:8081/employee/roles-department')
            .then(response => {
                this.departments = response.data.departments;
                this.roles = response.data.roles; // Gán dữ liệu từ API vào mảng departments
            })
            .catch(error => {
                console.error('Lỗi khi gọi API departments:', error);
            });
        this.$refs.usernameInput.addEventListener('blur', this.validateName);
        this.$refs.phoneInput.addEventListener('blur', this.validatePhone);
        this.$refs.addressInput.addEventListener('blur', this.validateAddress);
        this.$refs.emailInput.addEventListener('blur', this.validateEmail);
        this.$refs.accountInput.addEventListener('blur', this.validateAccount);
        this.$refs.passwordInput.addEventListener('blur', this.validatePassword);
        this.$refs.passwordInput.addEventListener('blur', this.validateRole);
        this.$refs.passwordInput.addEventListener('blur', this.validateDepament);

    },
    methods: {
        validateName() {
            if (!this.employee.userName.trim()) {
                this.invalidName = true;
            } else {
                this.invalidName = false;
            }
        }, validatePhone() {
            if (!this.employee.phoneNumber || !/^\d{10,11}$/.test(this.employee.phoneNumber)) {
                this.invalidPhone = true;
            } else {
                this.invalidPhone = false;
            }
        }, validateAddress() {
            if (!this.employee.address.trim()) {
                this.invalidAddress = true;
            } else {
                this.invalidAddress = false;
            }
        }, validateEmail() {
            if (!this.employee.email || !/\S+@\S+\.\S+/.test(this.employee.email)) {
                this.invalidEmail = true;
            } else {
                this.invalidEmail = false;
            }
        }, validateAccount() {
            if (!this.employee.nameAccount.trim()) {
                this.invalidAccount = true;
            } else {
                this.invalidAccount = false;
            }
        }, validatePassword() {
            if (!this.employee.passwordAccount.trim()) {
                this.invalidPassword = true;
            } else {
                this.invalidPassword = false;
            }
        }, validateRole() {
            if (this.employee.roleId != '') {
                this.invalidRole = true;
            } else {
                this.invalidRole = false;
            }
        }, validateDepament() {
            if (this.employee.departmentId != '') {
                this.invalidDepartment = true;
            } else {
                this.invalidDepartment = false;
            }
        }, updateEmployee() {
            axios.put(`http://localhost:8081/employee/update-employee/${this.$route.params.id}`, this.employee,{
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                }
            })
                .then(response => {
                    console.log('Cập nhật thông tin nhân viên thành công! ', response);
                })
                .catch(error => {
                    console.log('Có lỗi xảy ra khi cập nhật thông tin nhân viên: ', error);
                });
        },
    }
}
</script>

<style></style>