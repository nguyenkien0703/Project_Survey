<template>
    <div class="CreateQuestionContent">
        <div class="SaveContent mb-5">
            <div class="content-header ContentListSurvey">
                <div class="row">
                    <div class="col-6 text-left">
                        <h2>Tạo Khảo Sát</h2>
                    </div>
                    <div class="col-6 text-end">
                        <button type="submit" class="btn btn-success" @click="createFormSurvey">Lưu chính thức</button>
                    </div>
                </div>
            </div>
            <div class="container mt-5"></div>
        </div>
        <div class="container bg">
            <!-- create header survey -->
            <div class="title">
                <div class="mb-3">
                    <label for class="form-label">Tên khảo sát</label>
                    <input class="form-control" v-model="CreateSurveyDto.nameSurveyCreateSurvey"
                        placeholder="Tên khảo sát ..." />
                </div>
                <div class="mb-3">
                    <label for class="form-label">Nội dung :</label>
                    <textarea class="form-control" placeholder=" Mô tả khảo sát ..."
                        v-model="CreateSurveyDto.contentSurveyCreateSurvey" rows="1"></textarea>
                </div>
            </div>
            <!-- end create header survey -->

            <div class="content mt-5" v-for="(question, Qindex) in CreateSurveyDto.questions" :key="Qindex">
                <div class="row">
                    <div class="col-4">
                        <label for class="form-label">Câu hỏi:</label>
                        <input type="text" name v-model="CreateSurveyDto.questions[Qindex].nameQuestionCreateSurvey" id
                            class="form-control" placeholder="Viết câu hỏi của bạn ..." aria-describedby="helpId" />
                    </div>
                    <div class="col-4"></div>
                    <div class="col-4">
                        <div class="col-6">
                            <div class="mb-3">
                                <label for class="form-label">Chọn kiểu câu hỏi :</label>
                                <select class="form-select form-select-lg"
                                    v-model="CreateSurveyDto.questions[Qindex].questionTypeIdCreateSurvey">
                                    <option value>---Chọn---</option>
                                    <option v-for="type in types" :key="type.id" :value="type.id">
                                        {{ type.nameType }}
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div  v-if="CreateSurveyDto.questions[Qindex].questionTypeIdCreateSurvey === 1" class="d-none">
                        <div class="form-check " v-for="(option, index)  in CreateSurveyDto.questions[Qindex].options"
                            :key="index">
                            <label class="form-check-label" :for="'option' + index">
                                <div class="col">
                                    <div class="mb-3">
                                        <input type="text" class="form-control"
                                            v-model="CreateSurveyDto.questions[Qindex].options[index].nameOptionCreateSurvey"
                                            aria-describedby="helpId" />
                                    </div>
                                </div>
                            </label>
                        </div>
                        <div>
                            <button class="btn btn-sm btn-primary mt-2" @click="addOption(Qindex)">Thêm tùy chọn</button>
                        </div>
                    </div>
                    <div v-else>
                        <div class="form-check " v-for="(option, index)  in CreateSurveyDto.questions[Qindex].options"
                            :key="index">
                            <label class="form-check-label" :for="'option' + index">
                                <div class="col">
                                    <div class="mb-3">
                                        <input type="text" class="form-control"
                                            v-model="CreateSurveyDto.questions[Qindex].options[index].nameOptionCreateSurvey"
                                            aria-describedby="helpId" />
                                    </div>
                                </div>
                            </label>
                        </div>
                        <div>
                            <button class="btn btn-sm btn-primary mt-2" @click="addOption(Qindex)">Thêm tùy chọn</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center align-items-center mt-5">
                <button class="btn btn-success me-2" @click="addQuestion">Thêm câu hỏi</button>
            </div>
        </div>

    </div>
</template>
<script>
import axios from "axios";
export default {
    name: "CreateQuestionText",
    data() {
        return {
            currentQuestion: true, // Ban đầu đang tạo câu hỏi đầu tiên
            types: [],
            CreateSurveyDto: {
                nameSurveyCreateSurvey: "",
                contentSurveyCreateSurvey: "",
                questions: [
                    {
                        nameQuestionCreateSurvey: "",
                        questionTypeIdCreateSurvey: "",
                        options: [
                            {
                                nameOptionCreateSurvey: ""
                            }
                        ]
                    }
                ]
            }
        };
    },
    mounted() {
        axios
            .get("http://localhost:8081/question-type/types", {
                headers: {
                    Authorization: "Bearer " + sessionStorage.getItem("token")
                }
            })
            .then(response => {
                this.types = response.data;
            })
            .catch(error => {
                console.log(error);
            });
    },
    methods: {
        createFormSurvey() {
            const newCreateSurveyDto = {
                nameSurvey: this.CreateSurveyDto.nameSurveyCreateSurvey,
                contentSurvey: this.CreateSurveyDto.contentSurveyCreateSurvey,
                questions: []
            };
            // Lặp qua từng câu hỏi trong CreateSurveyDto
            for (let i = 0; i < this.CreateSurveyDto.questions.length; i++) {
                const question = this.CreateSurveyDto.questions[i];
                // Tạo đối tượng câu hỏi mới và gán các giá trị
                const newQuestion = {
                    nameQuestion: question.nameQuestionCreateSurvey || "",
                    questionType: question.questionTypeIdCreateSurvey,
                    optionQuestionDtos: []
                };
                for (let j = 0; j < question.options.length; j++) {
                    const option = question.options[j];
                    // Tạo đối tượng lựa chọn mới và gán các giá trị
                    const newOption = {
                        nameOption: option.nameOptionCreateSurvey || ""
                    };
                    newQuestion.optionQuestionDtos.push(newOption);
                }
                newCreateSurveyDto.questions.push(newQuestion);
            }
            axios.post("http://localhost:8081/createSurvey/surveys", newCreateSurveyDto, {
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                }
            }
            )
                .then(() => {
                    alert()
                    this.$router.push({ name: "surveys-admin" });
                })
                .catch(error => {
                    alert("Failed to add survey: " + error.response.data.message);
                });
        },
        addQuestion() {
            this.CreateSurveyDto.questions.push({
                nameQuestionCreateSurvey: "",
                questionTypeIdCreateSurvey: "",
                options: []
            });
        },
        addOption(index) {
            this.CreateSurveyDto.questions[index].options.push({
                nameOptionCreateSurvey: ''
            });
        }
    }
};
</script>

<style>
.bg {
    background-color: #eef0da;
    padding: 30px;
    border-radius: 30px;
}

.CreateQuestionContent .title,
.CreateQuestionContent .content {
    background-color: #e3e9f3;
    padding: 30px;
    border-radius: 30px;
}

.CreateQuestionContent .content {
    border-bottom: 1px dashed black;
}

.text-bg {
    background-color: #d9d9d9;
}
</style>