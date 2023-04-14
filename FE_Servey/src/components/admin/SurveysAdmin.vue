<template>
    <div class="div">
        <div class="row ContentListSurvey">
            <div class="col-6 ">
                <div class="text-left">
                    <h2>
                        Danh sách khảo sát
                    </h2>
                </div>
            </div>
            <div class="col-6 add">
                <router-link class="btn btn-primary text-right" to="/create-survey">Tạo mới khảo sát</router-link>
            </div>
        </div>
        <div class="container mt-5">
            <form @submit.prevent="searchSurveys()">
                <div class="row">
                    <div class="col-3">
                        <div class="mb-3">
                            <input type="text" class="form-control" v-model="searchName" id="" aria-describedby="helpId"
                                placeholder="Tên khảo sát">
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="mb-3">
                            <input type="date" id="inputPassword6" v-model="searchStartDate" class="form-control"
                                aria-describedby="passwordHelpInline">
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="mb-3">
                            <input type="date" class="form-control" v-model="searchEndDate" id=""
                                aria-describedby="passwordHelpInline" placeholder="endDate">
                        </div>
                    </div>

                    <div class="col-3 d-flex justify-content-center ">
                        <div class="div">
                            <button type="submit" class="btn  btn-success">Tìm kiếm</button>
                        </div>

                    </div>
                </div>
            </form>
            <table class="table view-table mt-5">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Chọn</th>
                        <th scope="col">Tên khảo sát</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Tiến độ </th>
                        <th scope="col">Ngày hết hạn</th>
                        <th scope="col">Chức Năng </th>
                    </tr>
                </thead>
                <tbody class="tbody">
                    <tr v-for="(survey, index) in surveys" :key="survey.id">
                        <th scope="">{{ (currentPage - 1) * perPage + index + 1 }}</th>
                        <td class="text-left">{{ survey.nameSurvey }}</td>
                        <td>{{ survey.numberOfParticipants }}</td>
                        <td class="text-left">{{ survey.progress }}</td>
                        <td>{{ survey.expiration }}</td>
                        <td><a class="btn btn-sm " @click="confirmSurvey(survey.id)"><i class="fa-solid fa-trash-can fa-2x "
                                    style="color: red;"></i></a>
                            <a class="btn btn-sm "><i class="fa-solid fa-pen-to-square fa-2x" style="color: blue;"
                                    data-bs-toggle="modal" data-bs-target="#exampleModalCenter"></i></a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <b-pagination v-model="currentPage" :total-rows="total" :per-page="perPage" @input="searchSurveys"
                align="center" class="my-3"></b-pagination>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: 'TableComp'
    , data() {
        return {
            surveys: [],
            total: 0,
            currentPage: 1,
            perPage: 5,
            searchName: '',
            searchStartDate: '',
            searchEndDate: '',
            surveyId: '',
        }
    },
    mounted() {
        this.searchSurveys();
    },
    methods: {
        searchSurveys(pageNumber = 1) {
            axios.get('http://localhost:8081/getSurveys', {
                params: {
                    nameSurvey: this.searchName,
                    startDate: this.searchStartDate,
                    endDate: this.searchEndDate,
                    pageNumber: pageNumber - 1,
                    pageSize: this.perPage,
                },
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                }
            }).then(response => {
                this.surveys = response.data.data.content;
                this.total = response.data.total;
                this.currentPage = pageNumber;
            }).catch(error => {
                console.log(error);
            });
        },
        updateCheckedStatus() {
            this.showDeleteButton = this.surveys.some(item => item.checked);
        },
        deleteSelectedemployees() {
            this.surveys = this.surveys.filter(item => !item.checked);
            this.showDeleteButton = false;
        },
        async deleteSurvey(surveyId) {
            try {
                // Gọi API DELETE với surveyId được truyền vào
                await axios.delete(`http://localhost:8081/survey/${surveyId}`, {
                    headers: {
                        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                    }
                });
                this.surveys = this.surveys.filter(survey => survey.id !== surveyId);
                // Xử lý logic sau khi xóa nhân viên thành công
            } catch (error) {
                console.log(error);
            }
        },
        confirmSurvey(id) {
            if (confirm("Bạn có chắc chắn muốn xóa không?")) {
                // Gọi hàm xóa tại đây
                this.deleteSurvey(id);
            }
        }
    }
}
</script>

<style>
.view-table {
    text-align: center;
}

.thead-light {
    background: #C2D7EB;
}

.tbody {
    background: #ECE8E8;
}

.removeAll {
    float: left;
    padding-left: 40px;
}

.removeAll .tn-cam {
    text-align: left;
    background: #E3C2B9;
}

.ContentListSurvey {
    padding: 40px;
    border-bottom: 1px solid #000000;
}

.ContentListSurvey .add {
    text-align: right;
}

.ContentListSurvey h2 {
    text-align: left;
}
</style>