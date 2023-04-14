<template>
    <div class="div">
        <div class="row ContentListSurvey">
            <div class="col-6 ">
                <div class="text-left">
                    <h2>
                        Danh sách nhân viên công ty
                    </h2>
                </div>
            </div>
            <div class="col-6 add">
                <router-link class="btn btn-primary text-right" to="/create-employee">Thêm Nhân Viên</router-link>
            </div>
        </div>
        <div class="container mt-5">
            <form @submit.prevent="searchEmployees()">
                <div class="row">
                    <div class="col-4">
                        <div class="mb-3">
                            <input type="text" class="form-control" v-model="searchName" id="" aria-describedby="helpId"
                                placeholder="Tên nhân viên">
                        </div>
                    </div>
                    <div class="col-4">

                        <div class="mb-3">
                            <input type="text" class="form-control" v-model="searchEmail" id="" aria-describedby="helpId"
                                placeholder="Gmail...">
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="row">
                            <div class="mb-3">
                                <input type="text" class="form-control" v-model="searchPhone" name="" id=""
                                    aria-describedby="helpId" placeholder="Số điện thoại ...">
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="mb-3">
                            <select class="form-select form-select-lg" v-model="searchDepartment" name="" id="">
                                <option value="">-- Vị trí --</option>
                                <option v-for="department in departments" :key="department.id" :value="department.id">{{
                                    department.nameDepartment }}</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="mb-3">
                            <select class="form-select form-select-lg" v-model="searchRole" name="" id="">
                                <option value="">-- Vai Trò --</option>
                                <option v-for="role in roles" :key="role.id" :value="role.id">{{
                                    role.nameRole }}</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class=" d-flex justify-content-center ">
                            <button type="submit" class="btn btn btn-success">Tìm kiếm</button>
                        </div>
                    </div>
                </div>

            </form>
            <table class="table view-table mt-3">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Tên</th>
                        <th scope="col">Vị trí</th>
                        <th scope="col">Gmail</th>
                        <th scope="col">Vị trò</th>
                        <th scope="col">Số điện thoại</th>
                        <th scope="col">Chức Năng </th>
                    </tr>
                </thead>
                <tbody class="tbody">
                    <tr v-for="(employee, index) in employees" :key="employee.id">
                        <td class="text-right">{{ (currentPage - 1) * perPage + index + 1 }}</td>
                        <td class="text-left">{{ employee.userName }}</td>
                        <td>{{ employee.departmentName }}</td>
                        <td class="text-left">{{ employee.email }}</td>
                        <td>{{ employee.roleName }}</td>
                        <td>{{ employee.phoneNumber }}</td>
                        <td><a class="btn btn-sm " @click="confirmDelete(employee.id)"><i
                                    class="fa-solid fa-trash-can fa-2x " style="color: red;"></i></a>
                            <router-link class="btn" :to="{ name: 'update-employee', params: { id: employee.id } }"><i
                                    class="fa-solid fa-pen-to-square fa-2x" style="color: blue;"></i></router-link>
                        </td>
                    </tr>
                </tbody>
            </table>
            <b-pagination v-model="currentPage" :total-rows="total" :per-page="perPage" @input="searchEmployees"
                align="center" class="my-3"></b-pagination>

        </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: 'ListEmployeeSurvey',
    data() {
        return {
            employees: [],
            total: 0,
            currentPage: 1,
            perPage: 5,
            searchName: '',
            searchEmail: '',
            searchDepartment: '',
            searchRole: '',
            searchPhone: '',
            employeeId: '',
            departments: [],
            roles: [],
        }
    },
    mounted() {
        this.searchEmployees(),
            // Gọi API "/departments" bằng Axios khi component được mounted
            axios.get('http://localhost:8081/employee/roles-department', {
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                }
            })
                .then(response => {
                    this.departments = response.data.departments;
                    this.roles = response.data.roles; // Gán dữ liệu từ API vào mảng departments
                })
                .catch(error => {
                    console.error('Lỗi khi gọi API departments:', error);
                });
    },
    methods: {
        searchEmployees(pageNumber = 1) {
            axios.get('http://localhost:8081/employee/search-count', {
                params: {
                    name: this.searchName.trim(),
                    email: this.searchEmail.trim(),
                    phone: this.searchPhone.trim(),
                    department: this.searchDepartment,
                    role: this.searchRole,
                    pageNumber: pageNumber - 1,
                    pageSize: this.perPage,

                },
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                }
            }).then(response => {
                this.employees = response.data.data.content;
                this.total = response.data.count;
                this.currentPage = pageNumber;
            }).catch(error => {
                console.log(error);
            });

        },
        updateCheckedStatus() {
            this.showDeleteButton = this.employees.some(item => item.checked);
        },
        deleteSelectedemployees() {
            this.employees = this.employees.filter(item => !item.checked);
            this.showDeleteButton = false;
        },
        async deleteEmployee(employeeId) {
            try {
                // Gọi API DELETE với employeeId được truyền vào
                await axios.delete(`http://localhost:8081/employee/${employeeId}`, {
                    headers: {
                        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                    }
                });
                this.employees = this.employees.filter(employee => employee.id !== employeeId);
                // Xử lý logic sau khi xóa nhân viên thành công
            } catch (error) {
                console.log(error);
            }
        }, confirmDelete(id) {
            if (confirm("Bạn có chắc chắn muốn xóa không?")) {
                // Gọi hàm xóa tại đây
                this.deleteEmployee(id);
            }
        }
    }
}
</script>

<style></style>