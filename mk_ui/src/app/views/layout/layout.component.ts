import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { UserProvider } from '../../providers/provider';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
    constructor(public rou: Router, private pro: UserProvider) { }
    public count: any = 0;
    public cssNav: any;

    ngOnInit() {
        this.cssNav = document.getElementsByClassName("nav-item");
        for (var i = 0; i < this.cssNav.length; i++) {
            this.cssNav[i].removeAttribute("tabindex");
        }

        document.getElementById("dashboard").style.backgroundColor = "#1CB09A";
        this.rou.navigate(['/dashboard']);
    }

    public signOut() {
        let answer = confirm("You want to sign out?");
        if (answer) {
            this.pro.signOut();
        }
    }

    public resize() {
        let css = document.getElementsByClassName("nav-link-text") as HTMLCollectionOf<HTMLElement>;
        if (this.count == 0) {
            this.count++;
            document.getElementById("exampleAccordion").style.width = "4%";
            //document.getElementById("resize").style.setProperty("left", "5%", "important");
            for (var i = 0; i < css.length; i++) {
                css[i].style.display = "none";
            }
            document.getElementById("content-wrapper").style.animationName = "content-left";
            document.getElementById("content-wrapper").style.animationDuration = "1s";
            document.getElementById("content-wrapper").style.animationFillMode = "forwards";
            document.getElementById("rotateIcon").style.transform = "rotate(180deg)";
        }
        else {
            this.count = 0;
            document.getElementById("exampleAccordion").style.width = "16.7%";
            setTimeout(function () {
                //document.getElementById("resize").style.setProperty("left", "16.7%", "important");
                for (var i = 0; i < css.length; i++) {
                    css[i].style.display = "initial";
                }
            }, 1000);
            document.getElementById("content-wrapper").style.animationName = "content-right";
            document.getElementById("content-wrapper").style.animationDuration = "1s";
            document.getElementById("content-wrapper").style.animationFillMode = "forwards";
            document.getElementById("rotateIcon").style.transform = "rotate(0)";
        }
    }

    public active(str: string) {
        for (var i = 0; i < this.cssNav.length; i++) {
            this.cssNav[i].style.backgroundColor = "#343a40";
        }

        document.getElementById(str).style.backgroundColor = "#1CB09A";
        this.rou.navigate(['/' + str]);
    }
}

