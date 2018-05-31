import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { Router, ActivatedRoute, Params } from '@angular/router';
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
    public parent_id: any;
    public selEdit: any = "";
    public vm: any = { text: "", description: "", parentId: null, icon: "question.png" };
    public count: any;
    public apiURL: string = "../../../../assets/img/";
    public loader: boolean;
    public function = "overview";

    @ViewChild('confirmModal') public confirmModal: ModalDirective;
    @ViewChild('confirmModal1') public confirmModal1: ModalDirective;
    @ViewChild('iconModal') public iconModal: ModalDirective;

    constructor(private proIncome: IncomeProvider,
        private proExpense: ExpenseProvider,
        private proCommon: CommonProvider,
        private rou: Router,
        private act: ActivatedRoute) { }

    ngOnInit() {
        this.act.params.subscribe((params: Params) => {
            document.getElementById(this.function).style.display = "none";
            this.function = params["function"];
            document.getElementById(this.function).style.display = "block";
            this.vm = {};
            this.vm.icon = 'question.png';
            this.getImages();
        });

        this.loadExpense();
    }

    public loadIncome() {
        this.loader = true;
        this.tab = "Income";
        document.getElementById("tabExpense").style.backgroundColor = "white";
        document.getElementById("tabExpense").style.color = "black";
        document.getElementById("tabIncome").style.backgroundColor = "#1CB09A";
        document.getElementById("tabIncome").style.color = "white";

        this.proIncome.search().subscribe((rsp: any) => {
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
            this.loader = false;
        }, err => console.log(err));
    }

    public loadExpense() {
        this.loader = true;
        this.tab = "Expense";
        document.getElementById("tabExpense").style.backgroundColor = "#1CB09A";
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
            this.loader = false;
        }, err => console.log(err));
    }

    public redirectEdit(id: any, count: any) {
        this.count = count;
        this.rou.navigate(['/category/edit']);
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
                    this.rou.navigate(['/category/overview']);
                    this.loadExpense();
                    this.confirmModal1.show();
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
        else {
            this.proIncome.save(this.vm).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.rou.navigate(['/category/overview']);
                    this.confirmModal1.show();
                    this.loadIncome();
                }
                else {
                    console.log(rsp.message);
                }
            })
        }
    }

    public delete(id: any) {
        this.loader = true;
        if (this.tab == "Expense") {
            this.proExpense.delete(id).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.rou.navigate(['/category/overview']);
                    this.loadExpense();
                    this.confirmModal.hide();
                }
                else {
                    console.log(rsp.message);
                }
                this.loader = false;
            })
        }
        else {
            this.proIncome.delete(id).subscribe((rsp: any) => {
                if (rsp.status === 'success') {
                    this.rou.navigate(['/category/overview']);
                    this.loadIncome();
                    this.confirmModal.hide();
                }
                else {
                    console.log(rsp.message);
                }
                this.loader = false;
            })
        }
    }

    public selectIcon(iconName: string) {
        this.vm.icon = iconName;
        this.iconModal.hide();
    }

    private getImages() {
        this.proCommon.getImages(this.tab).subscribe((rsp: any) => {
            if (rsp.status === 'success') {
                this.lstImages = rsp.result.data;
            }
            else {
                console.log(rsp.message);
            }
        })
    }
}