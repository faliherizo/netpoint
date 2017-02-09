/**
 * 
 */
package com.netPoint.applications.site.displaytag.decorators;

import java.io.Serializable;
import java.util.Random;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Faliherizo
 *
 */
public class ReportableListObject extends Object implements Comparable, Serializable{
	private static final long serialVersionUID = 899149338534L;
	 private static Random random = new Random();
	 private static String[] cities = //
			      {"Roma", "Olympia", "Neapolis", "Carthago"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	private static String[] projects = //
			        {"Taxes", "Arts", "Army", "Gladiators"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	private String city;
	private String project;
	private String task;
	private double amount;
	private int count;
	public ReportableListObject()
	     {
	         this.amount = (random.nextInt(99999) + 1) / 100;
	         this.city = cities[random.nextInt(cities.length)];
	         this.project = projects[random.nextInt(projects.length)];
	         this.task = RandomSampleUtil.getRandomSentence(4);
	         this.count = random.nextInt(10);
	     }
	public String getCity()
	     {
	         return this.city;
	     }
	public int getCount()
	    {
	        return this.count;
	    }
	 public String getProject()
	     {
	         return this.project;
	     }
	 public String getTask()
	     {
	         return this.task;
	     }
	 public double getAmount()
	     {
	         return this.amount;
	     }
	@Override
	public int compareTo(Object object)
	    {
	        ReportableListObject myClass = (ReportableListObject) object;
	        return new CompareToBuilder().append(this.project, myClass.project).append(this.amount, myClass.amount).append(
	            this.city,
	            myClass.city).append(this.task, myClass.task).toComparison();

	    }
	  public String toString()
	      {
	          return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE) //
	              .append("project", this.project) //$NON-NLS-1$
	              .append("amount", this.amount) //$NON-NLS-1$
	              .append("city", this.city) //$NON-NLS-1$
	              .append("task", this.task) //$NON-NLS-1$
	              .toString();
	      }

}
