import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { Router } from '@angular/router';
import {
    UserProvider,
    IncomeProvider,
    ExpenseProvider,
    CommonProvider
} from '../../providers/provider';

@Component({
    selector: 'app-category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.css']
})

export class CategoryComponent implements OnInit {
    public lstParent = [];
    public lstChild = [];
    public lstParentTmp = [];
    public lstChildTmp = [];
    public lstImages = [];
    public keyword = '';
    public isShow: boolean = true;
    public tab: string = "";
    public isCollapsed: boolean = true;
    public isNull: boolean = true;
    public parent_id: any;
    public selEdit: any = "";
    public vm: any = { text: "", description: "", parentId: null, icon: "question.png" };
    public vmImage: any = { type: "", value: "" };
    public count: any;
    public apiURL: string = "../../../../assets/img/";

    @ViewChild('confirmModal') public confirmModal: ModalDirective;
    @ViewChild('iconModal') public iconModal: ModalDirective;

    constructor(private proIncome: IncomeProvider,
        private proExpense: ExpenseProvider,
        private proCommon: CommonProvider,
        private rou: Router, ) { }

    ngOnInit() {
        this.loadExpense();
    }

    public loadIncome() {
        this.tab = "Income";
        document.getElementById("tabExpense").style.backgroundColor = "white";
        document.getElementById("tabExpense").style.color = "black";
        document.getElementById("tabIncome").style.backgroundColor = "#017DE3";
        document.getElementById("tabIncome").style.color = "white";

        this.proIncome.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.isShow = false;
                this.lstParent = rsp.result.parent;
                this.lstParentTmp = this.lstParent;
                this.lstChild = rsp.result.child;
                this.lstChildTmp = this.lstChild;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public loadExpense() {
        this.tab = "Expense";
        document.getElementById("tabExpense").style.backgroundColor = "#017DE3";
        document.getElementById("tabExpense").style.color = "white";
        document.getElementById("tabIncome").style.backgroundColor = "white";
        document.getElementById("tabIncome").style.color = "black";

        this.proExpense.search().subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.isShow = true;
                this.lstParent = rsp.result.parent;
                this.lstParentTmp = this.lstParent;
                this.lstChild = rsp.result.child;
                this.lstChildTmp = this.lstChild;
            }
            else {
                console.log(rsp.message);
            }
        }, err => console.log(err));
    }

    public redirectAdd() {
        this.vm.text = '';
        this.vm.parentId = '';
        this.vm.description = '';
        this.vm.icon = 'question.png';

        document.getElementById("divAdd").style.display = "block";
        document.getElementById("divEdit").style.display = "none";
        document.getElementById("divCategory").style.display = "none";

        this.getImages();
    }

    public redirectEdit(parentId: any, id: any, count: any) {
        parentId == null ? this.isNull = true : this.isNull = false;
        this.count = count;

        document.getElementById("divEdit").style.display = "block";
        document.getElementById("divAdd").style.display = "none";
        document.getElementById("divCategory").style.display = "none";

        this.getImages();

        if (this.tab == "Expense") {
            this.proExpense.getById(id).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.vm = rsp.result;
                    this.selEdit = rsp.result.parentId;

                }
                else {
                    console.log(rsp.message);
                }
            })
        }
        else {
            this.proIncome.getById(id).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.vm = rsp.result;
                    this.selEdit = rsp.result.parentId;
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
    }

    public redirectCategory() {
        document.getElementById("divAdd").style.display = "none";
        document.getElementById("divEdit").style.display = "none";
        document.getElementById("divCategory").style.display = "block";
    }

    public search() {
        let keyword = this.keyword.toLowerCase();
        this.lstParentTmp = this.lstParent;
        this.lstChildTmp = this.lstChild;
        if (keyword != "" || keyword != null) {
            this.lstParentTmp = [];
            this.lstParent.forEach(obj => {
                let text = obj.text.toLowerCase();
                if (text.includes(keyword)) {
                    this.lstParentTmp.push(obj);
                }
            });
            this.lstChildTmp = [];
            this.lstChild.forEach(obj => {
                let text = obj.text.toLowerCase();
                if (text.includes(keyword)) {
                    this.lstChildTmp.push(obj);
                }
            });
        }
    }

    public changeIcon(id: any) {
        let arrow = document.getElementById("arrow" + id).style.transform;

        if (arrow == "") {
            document.getElementById("arrow" + id).style.transform = "rotate(180deg)";
        }
        else {
            document.getElementById("arrow" + id).style.transform = null;
        }
    }

    public saveCategory(valid: boolean) {
        if (!valid) {
            return;
        }

        if (this.tab == "Expense") {
            this.proExpense.save(this.vm).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.redirectCategory();
                    this.loadExpense();
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
        else {
            this.proIncome.save(this.vm).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.redirectCategory();
                    this.loadIncome();
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
    }

    public delete(id: any) {
        if (this.tab == "Expense") {
            this.proExpense.delete(id).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.redirectCategory();
                    this.loadExpense();
                    this.confirmModal.hide();
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
        else {
            this.proIncome.delete(id).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.redirectCategory();
                    this.loadIncome();
                    this.confirmModal.hide();
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
    }

    public getImages() {
        this.vmImage = { type: "Image", value: this.tab };
        this.proCommon.getImages(this.vmImage).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstImages = rsp.result.data;

            }
            else {
                console.log(rsp.message);
            }
            console.log(rsp);
        })
    }

    public selectIcon(iconName: string) {
        this.vm.icon = iconName;
        this.iconModal.hide();
    }
}