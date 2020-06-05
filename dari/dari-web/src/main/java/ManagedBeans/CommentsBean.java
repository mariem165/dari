package ManagedBeans;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import Services.CommentService;
import Services.MeublesServices;
import tn.esprit.dari.entities.Comment;
import tn.esprit.dari.entities.Meubles;

@ManagedBean(name="CommentsBean")
@SessionScoped
public class CommentsBean {
 public Comment a;
 @EJB
 CommentService Cs;
 @EJB
 MeublesServices MS;
 
 private String content;

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}
public String PostComment(String id)
{

 Comment C= new Comment();
 System.out.print(id);
 C.setMeubles(MeubleBean.MMM);
 C.setTime(Calendar.getInstance().getTime());
 C.setUser(null);
 C.setContent(this.content);
 Cs.CreateComment(C);
 return "meubles2";
}
}