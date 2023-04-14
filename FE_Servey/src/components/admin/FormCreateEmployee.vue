<template>
  <div class="div">
    <div class="row ContentListSurvey">
      <div class="col-6 ">
        <div class="text-left">
          <h2>
            Tạo nhân viên
          </h2>
        </div>
      </div>
    </div>
    <form class="d-flex" @submit.prevent="addEmployee">
      <div class="container bg-form mt-4">
        <div class="row bg-row">
          <div class="col-7">
            <div class="mb-3">
              <label for="" class="form-label">Họ và tên :</label>
              <input type="text" class="form-control  form-el" placeholder="" aria-describedby="helpId"
                v-model="employee.username" :class="{ 'is-invalid': invalidName }" required @blur="validateName">
              <div class="invalid-feedback">
                Vui lòng nhập tên !!
              </div>
            </div>
            <div class="mb-3">
              <label for="" class="form-label">Số điện thoại:</label>
              <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                v-model="employee.phone" :class="{ 'is-invalid': invalidPhone }" required @blur="validatePhone">
              <div class="invalid-feedback">
                Vui lòng nhập số điện thoại (10-11 số).
              </div>
            </div>
            <div class="mb-3">
              <label for="" class="form-label">Địa chỉ :</label>
              <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                v-model="employee.address" :class="{ 'is-invalid': invalidAddress }" required @blur="validateAddress">
              <div class="invalid-feedback">
                Vui lòng nhập địa chỉ !!
              </div>
            </div>
            <div class="row">
              <div class="col-6">
                <div class="mb-3">
                  <select class="form-select form-select-lg" v-model="employee.department">
                    <option value="">-- Vị trí --</option>
                    <option v-for="department in departments" :key="department.id" :value="department.id"
                      @blur="validateDepament">
                      {{ department.nameDepartment }}</option>
                  </select>
                  <div v-if="invalidDepartment" class="invalid-feedback">
                    Vui lòng chọn vị trí !!
                  </div>
                </div>
              </div>
              <div class="col-6">
                <div class="mb-3 ">
                  <select class="form-select form-select-lg" v-model="employee.role" @blur="validateRole">
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
                v-model="employee.email" :class="{ 'is-invalid': invalidEmail }" required @blur="validateEmail">
              <div class="invalid-feedback">
                Vui lòng nhập Email và đúng định dạng !!
              </div>
            </div>
            <div class="mb-3">
              <label for="" class="form-label">Tài khoản :</label>
              <input type="text" class="form-control form-el" placeholder="" aria-describedby="helpId"
                v-model="employee.account" :class="{ 'is-invalid': invalidAccount }" required @blur="validateAccount">
              <div class="invalid-feedback">
                Vui lòng nhập tên tài khoản !!
              </div>
            </div>
            <div class="mb-3">
              <label for="" class="form-label">Mật khẩu :</label>
              <input type="password" class="form-control form-el" placeholder="" aria-describedby="helpId"
                v-model="employee.password" :class="{ 'is-invalid': invalidPassword }" required @blur="validatePassword">
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
          <button type="button" class="btn  btn-danger me-5">Hủy</button>
          <button type="submit" class="btn btn-success">Thêm</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'FormCreateEmployee',
  data: () => ({
    searchDepartment: '',
    invalidName: false,
    invalidPhone: false,
    invalidAddress: false,
    invalidEmail: false,
    invalidAccount: false,
    invalidPassword: false,
    invalidRole: false,
    invalidDepartment: false,
    departments: [],
    roles: [],
    employee: {
      email: '',
      username: '',
      phone: '',
      account: '',
      password: '',
      address: '',
      department: '',
      sex: '',
      role: ''
    },
  }),
  mounted() {
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
    this.$refs.usernameInput.addEventListener('blur', this.validateName);
    this.$refs.phoneInput.addEventListener('blur', this.validatePhone);
    this.$refs.addressInput.addEventListener('blur', this.validateAddress);
    this.$refs.emailInput.addEventListener('blur', this.validateEmail);
    this.$refs.accountInput.addEventListener('blur', this.validateAccount);
    this.$refs.passwordInput.addEventListener('blur', this.validatePassword);
  }
  ,
  methods: {
    onSubmit() {
      alert('Thêm Thành công!');
    }, addEmployee() {
      const newEmployee = {
        userName: this.employee.username,
        phoneNumber: this.employee.phone,
        address: this.employee.address,
        departmentId: this.employee.department,
        roleId: this.employee.role,
        email: this.employee.email,
        nameAccount: this.employee.account,
        passwordAccount: this.employee.password,
        eSex: this.employee.sex
      };
      axios.post('http://localhost:8081/employee/create-employee', newEmployee, {
        headers: {
          'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        }
      })
        .then(() => {
          alert('Employee added successfully!');
          // Reset the form
          this.employee.email = '';
          this.employee.username = '';
          this.employee.address = '';
          this.employee.account = '';
          this.employee.phone = '';
          this.employee.password = '';
          this.employee.department = '';
          this.employee.sex = '';
          this.employee.role = '';
        })
        .catch((error) => {
          alert('Failed to add employee: ' + error.response.data.message);
        });
      this.$router.push({ name: 'list-employee' });
    },
    validateName() {
      if (!this.employee.username.trim()) {
        this.invalidName = true;
      } else {
        this.invalidName = false;
      }
    }, validatePhone() {
      if (!this.employee.phone || !/^\d{10,11}$/.test(this.employee.phone)) {
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
      if (!this.employee.account.trim()) {
        this.invalidAccount = true;
      } else {
        this.invalidAccount = false;
      }
    }, validatePassword() {
      if (!this.employee.password.trim()) {
        this.invalidPassword = true;
      } else {
        this.invalidPassword = false;
      }
    }, validateRole() {
      if (this.employee.role != '') {
        this.invalidRole = true;
      } else {
        this.invalidRole = false;
      }
    }, validateDepament() {
      if (this.employee.role != '') {
        this.invalidRole = true;
      } else {
        this.invalidRole = false;
      }
    }
  }
}
</script>

<style>
.form-el {
  /* Loại bỏ border */
  border: 1px dotted #000;
  /* Thêm border-bottom với kiểu chấm chấm và màu đen */
  outline: none;
  /* Loại bỏ viền xung quanh trường nhập liệu */
}

.bg-form {
  background-color: #ECE8E8;
  padding: 40px 40px 20px 40px;
  border-radius: 30px;
}

.bg-row {
  background-color: #CBDBEB;
  padding: 20px;
  border-radius: 20px;
}
</style>